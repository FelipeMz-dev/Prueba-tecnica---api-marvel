package com.mz_dev.prueba_tecnica.ui.view.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mz_dev.prueba_tecnica.R
import com.mz_dev.prueba_tecnica.data.model.Character

@Composable
fun CharacterCard(
    item: Character,
    onClickItem: () -> Unit,
    onClickFavorite: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(2.dp)

        ) {
            val isLoadedImage = remember { mutableStateOf(false) }
            Box {
                AsyncImage(
                    model = item.thumbnail,
                    contentDescription = item.name,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable { onClickItem() },
                    error = painterResource(id = R.drawable.baseline_warning_24),
                    onLoading = { isLoadedImage.value = false },
                    onSuccess = { isLoadedImage.value = true }
                )
                if (!isLoadedImage.value) CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
                        .aspectRatio(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = if (item.favorite) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Favorite",
                    modifier = Modifier.clickable { onClickFavorite() }
                )
            }
        }
    }
}