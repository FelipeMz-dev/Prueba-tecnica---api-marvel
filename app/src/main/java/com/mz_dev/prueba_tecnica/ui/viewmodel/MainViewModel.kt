package com.mz_dev.prueba_tecnica.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.domain.GetCharactersUseCase
import com.mz_dev.prueba_tecnica.domain.LoadDataCharactersUseCase
import com.mz_dev.prueba_tecnica.domain.UpdateCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadRemoteDataIntoDbUseCase: LoadDataCharactersUseCase,
    private val updateCharacterUseCase: UpdateCharacterUseCase
) : MVIBaseViewModel<MainViewModel.UiState, MainViewModel.UiEvent>() {

    init {
        eventHandler(UiEvent.LoadData)
    }

    private fun loadData() {
        viewModelScope.launch {
            updateUi { it.copy(loading = true) }
            loadRemoteDataIntoDbUseCase(500)
            getCharactersUseCase().collect { characters ->
                updateUi {
                    it.copy(
                        characters = characters,
                        filteredCharacter = characters,
                        loading = false)
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

    private fun onSearchCharacters(query: String) {
        viewModelScope.launch {
            updateUi {
                it.copy(filteredCharacter = it.characters.filter {character ->
                    character.name.lowercase().contains(query.lowercase())
                })
            }
        }
    }

    private fun onFillCharacters() {
        viewModelScope.launch {
            updateUi {
                it.copy(filteredCharacter = it.characters)
            }
        }
    }

    private fun onFilterCharactersByFavorite(){
        viewModelScope.launch {
            updateUi {
                if (it.isFavoriteFiltered) {
                    it.copy(isFavoriteFiltered = false,
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

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList(),
        val openCharacter: Character? = null,
        val isOpenCharacter: Boolean = false,
        val isSearching: Boolean = false,
        val filteredCharacter: List<Character> = emptyList(),
        val isFavoriteFiltered: Boolean = false
    ) : State

    sealed interface UiEvent : Event {
        object LoadData : UiEvent
        data class OpenCharacter(val character: Character) : UiEvent
        object CloseCharacter : UiEvent
        data class ChangeFavorite(val character: Character) : UiEvent
        object ChangeSearchState : UiEvent
        data class SearchCharacter(val query: String) : UiEvent
        object FillCharacters : UiEvent
        object FilterCharactersByFavorite : UiEvent
    }

    override fun initState(): UiState = UiState()

    override fun intentHandler() {
        executeIntent { event ->
            when (event) {
                is UiEvent.LoadData -> loadData()
                is UiEvent.OpenCharacter -> onOpenCharacter(event.character)
                is UiEvent.CloseCharacter -> onCloseCharacter()
                is UiEvent.ChangeFavorite -> onChangeFavorite(event.character)
                is UiEvent.ChangeSearchState -> onChangeSearchState()
                is UiEvent.SearchCharacter -> onSearchCharacters(event.query)
                is UiEvent.FillCharacters -> onFillCharacters()
                is UiEvent.FilterCharactersByFavorite -> onFilterCharactersByFavorite()
            }
        }
    }
}