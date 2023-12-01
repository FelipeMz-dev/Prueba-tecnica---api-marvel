package com.mz_dev.prueba_tecnica.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.domain.GetCharactersUseCase
import com.mz_dev.prueba_tecnica.domain.LoadDataCharactersUseCase
import com.mz_dev.prueba_tecnica.domain.UpdateCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadRemoteDataIntoDbUseCase: LoadDataCharactersUseCase,
    private val updateDataUseCase: UpdateCharacterUseCase
) : MVIBaseViewModel<MainViewModel.UiState, MainViewModel.UiEvent>() {

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            updateUi { it.copy(loading = true) }
            loadRemoteDataIntoDbUseCase(20)
            getCharactersUseCase().collect{characters ->
                updateUi { it.copy(characters = characters, loading = false)
                }
            }
        }
    }

    fun onChangeFavorite(character: Character) {
        viewModelScope.launch {
            val newCharacter = character.copy(favorite = !character.favorite)
            updateDataUseCase(newCharacter)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val characters: List<Character> = emptyList()
    ): State

    sealed interface UiEvent : Event {
        object LoadData: UiEvent
        data class ChangeFavorite(val character: Character) : UiEvent
    }

    override fun initState(): UiState = UiState()

    override fun intentHandler() {
        executeIntent { event ->
            when (event) {
                is UiEvent.LoadData -> loadData()
                is UiEvent.ChangeFavorite -> onChangeFavorite(event.character)
            }
        }
    }
}