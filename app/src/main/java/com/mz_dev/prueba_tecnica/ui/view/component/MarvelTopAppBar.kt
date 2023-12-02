package com.mz_dev.prueba_tecnica.ui.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mz_dev.prueba_tecnica.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelTopAppBar(
    viewModel: MainViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val isSearching = state.isSearching
    var searchText by remember { mutableStateOf("") }
    TopAppBar(
        title = {
            Text(
                "Marvel Characters",
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            if (isSearching) {
                TextFieldSearch(
                    query = searchText,
                    onQueryChange = {
                        searchText = it
                        viewModel.eventHandler(MainViewModel.UiEvent.SearchCharacter(it))
                    },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    icon = Icons.Default.Close,
                    color = if (searchText.isNotEmpty()) Color.LightGray else Color.White
                ) {
                    if (searchText.isEmpty()) {
                        viewModel.eventHandler(MainViewModel.UiEvent.ChangeSearchState)
                    }
                    searchText = ""
                }
                Spacer(modifier = Modifier.width(8.dp))
            } else {
                ActionButton(icon = Icons.Default.Favorite) {
                    viewModel.eventHandler(MainViewModel.UiEvent.FilterCharactersByFavorite)
                }
                Spacer(modifier = Modifier.width(8.dp))
                ActionButton(icon = Icons.Default.Search) {
                    viewModel.eventHandler(MainViewModel.UiEvent.ChangeSearchState)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    )
}

