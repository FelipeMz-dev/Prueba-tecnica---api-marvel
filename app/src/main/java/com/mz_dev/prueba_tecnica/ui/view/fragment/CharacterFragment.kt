package com.mz_dev.prueba_tecnica.ui.view.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.ui.view.component.card.CharacterCard

@Composable
fun CharacterFragment(
    characters: List<Character>,
    onChangeFavorite: (Character) -> Unit,
    onOpenCharacter: (Character) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(characters) {
            CharacterCard(
                item = it,
                onClickItem = { onOpenCharacter(it) },
                onClickFavorite = { onChangeFavorite(it) }
            )
        }
    }
}