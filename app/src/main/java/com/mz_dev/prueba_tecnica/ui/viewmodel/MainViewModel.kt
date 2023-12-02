package com.mz_dev.prueba_tecnica.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.data.model.Serie
import com.mz_dev.prueba_tecnica.domain.GetCharactersUseCase
import com.mz_dev.prueba_tecnica.domain.GetSeriesUseCase
import com.mz_dev.prueba_tecnica.domain.LoadDataUseCase
import com.mz_dev.prueba_tecnica.domain.UpdateCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSeriesUseCase: GetSeriesUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    private val updateCharacterUseCase: UpdateCharacterUseCase
) : MVIBaseViewModel<MainViewModel.UiState, MainViewModel.UiEvent>() {

    companion object {
        const val TAG_CHARACTER = "Characters"
        const val TAG_SERIES = "Series"
    }

    init {
        eventHandler(UiEvent.LoadData)
        eventHandler(UiEvent.GetCharacters)
        eventHandler(UiEvent.GetSeries)
    }

    private fun loadData() {
        viewModelScope.launch {
            updateUi { it.copy(loading = true) }
            loadDataUseCase(500)
        }
    }

    private fun onGetCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().collect { characters ->
                updateUi {
                    if (it.series.isNotEmpty()) {
                        it.copy(
                            characters = characters,
                            filteredCharacter = characters,
                            loading = false
                        )
                    } else {
                        it.copy(
                            characters = characters,
                            filteredCharacter = characters
                        )
                    }
                }
            }
        }
    }

    private fun onGetSeries() {
        viewModelScope.launch {
            getSeriesUseCase().collect { series ->
                updateUi {
                    if (it.characters.isNotEmpty()) {
                        it.copy(
                            series = series,
                            filteredSeries = series,
                            loading = false
                        )
                    } else {
                        it.copy(
                            series = series,
                            filteredSeries = series
                        )
                    }
                }
            }
        }
    }

    private fun onChangeFavorite(character: Character) {
        viewModelScope.launch {
            val newCharacter = character.copy(favorite = !character.favorite)
            updateCharacterUseCase(newCharacter)
        }
    }

    private fun onOpenCharacter(character: Character) {
        viewModelScope.launch {
            updateUi { it.copy(openCharacter = character, isOpenCharacter = !it.isOpenCharacter) }
        }
    }

    private fun onCloseCharacter() {
        viewModelScope.launch {
            updateUi { it.copy(isOpenCharacter = false) }
        }
    }

    private fun onChangeSearchState() {
        viewModelScope.launch {
            updateUi { it.copy(isSearching = !it.isSearching) }
        }
    }

    private fun onSearchItems(query: String) {
        viewModelScope.launch {
            updateUi {
                if (it.sectionSelected == TAG_CHARACTER) {
                    it.copy(filteredCharacter = it.characters.filter { character ->
                        character.name.lowercase().contains(query.lowercase())
                    })
                } else {
                    it.copy(filteredSeries = it.series.filter { serie ->
                        serie.title.lowercase().contains(query.lowercase())
                    })
                }
            }
        }
    }

    private fun onFillItems() {
        viewModelScope.launch {
            updateUi {
                if (it.sectionSelected == TAG_CHARACTER) {
                    it.copy(filteredCharacter = it.characters)
                } else {
                    it.copy(filteredSeries = it.series)
                }
            }
        }
    }

    private fun onFilterCharactersByFavorite() {
        viewModelScope.launch {
            updateUi {
                if (it.isFavoriteFiltered) {
                    it.copy(
                        isFavoriteFiltered = false,
                        filteredCharacter = it.characters
                    )
                } else {
                    it.copy(
                        isFavoriteFiltered = true,
                        filteredCharacter = it.characters.filter { character ->
                            character.favorite
                        }
                    )
                }
            }
        }
    }

    private fun onChangeSectionSelected(section: String) {
        viewModelScope.launch {
            updateUi { it.copy(sectionSelected = section) }
        }
    }

    data class UiState(
        val loading: Boolean = true,
        val characters: List<Character> = emptyList(),
        val series: List<Serie> = emptyList(),
        val openCharacter: Character? = null,
        val isOpenCharacter: Boolean = false,
        val isSearching: Boolean = false,
        val filteredCharacter: List<Character> = emptyList(),
        val filteredSeries: List<Serie> = emptyList(),
        val isFavoriteFiltered: Boolean = false,
        val sectionSelected: String = TAG_CHARACTER
    ) : State

    sealed interface UiEvent : Event {
        object LoadData : UiEvent
        object GetCharacters : UiEvent
        object GetSeries : UiEvent
        data class OpenCharacter(val character: Character) : UiEvent
        object CloseCharacter : UiEvent
        data class ChangeFavorite(val character: Character) : UiEvent
        object ChangeSearchState : UiEvent
        data class SearchItem(val query: String) : UiEvent
        object FillItems : UiEvent
        object FilterCharactersByFavorite : UiEvent
        data class ChangeSectionSelected(val section: String) : UiEvent
    }

    override fun initState(): UiState = UiState()

    override fun intentHandler() {
        executeIntent { event ->
            when (event) {
                is UiEvent.LoadData -> loadData()
                is UiEvent.GetCharacters -> onGetCharacters()
                is UiEvent.GetSeries -> onGetSeries()
                is UiEvent.OpenCharacter -> onOpenCharacter(event.character)
                is UiEvent.CloseCharacter -> onCloseCharacter()
                is UiEvent.ChangeFavorite -> onChangeFavorite(event.character)
                is UiEvent.ChangeSearchState -> onChangeSearchState()
                is UiEvent.SearchItem -> onSearchItems(event.query)
                is UiEvent.FillItems -> onFillItems()
                is UiEvent.FilterCharactersByFavorite -> onFilterCharactersByFavorite()
                is UiEvent.ChangeSectionSelected -> onChangeSectionSelected(event.section)
            }
        }
    }
}