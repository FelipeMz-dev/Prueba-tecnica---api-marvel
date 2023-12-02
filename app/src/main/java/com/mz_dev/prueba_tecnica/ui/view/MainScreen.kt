package com.mz_dev.prueba_tecnica.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.mz_dev.prueba_tecnica.ui.view.component.LoadingScreen
import com.mz_dev.prueba_tecnica.ui.view.component.MarvelTopAppBar
import com.mz_dev.prueba_tecnica.ui.view.component.OpenCharacterDialog
import com.mz_dev.prueba_tecnica.ui.view.component.TextNotResultsFound
import com.mz_dev.prueba_tecnica.ui.view.fragment.CharacterFragment
import com.mz_dev.prueba_tecnica.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val state by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .alpha(
                    if (state.isOpenCharacter) 0.5f else 1f
                ),
            topBar = { MarvelTopAppBar(viewModel) }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                when {
                    state.loading -> {
                        LoadingScreen()
                    }

                    state.filteredCharacter.isEmpty() -> {
                        TextNotResultsFound()
                    }

                    state.characters.isNotEmpty() -> {
                        CharacterFragment(
                            characters = state.filteredCharacter,
                            onOpenCharacter = {
                                viewModel.eventHandler(MainViewModel.UiEvent.OpenCharacter(it))
                            },
                            onChangeFavorite = {
                                viewModel.eventHandler(MainViewModel.UiEvent.ChangeFavorite(it))
                            }
                        )
                    }
                }
            }
        }

        if (state.isOpenCharacter) {
            state.openCharacter?.let { character ->
                OpenCharacterDialog(character = character) {
                    viewModel.eventHandler(MainViewModel.UiEvent.CloseCharacter)
                }
            }
        }
    }
}

