package com.mz_dev.prueba_tecnica.ui.view.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.mz_dev.prueba_tecnica.data.model.Serie

@Composable
fun SerieCard(serie: Serie) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row {
            val isLoadedImage = remember { mutableStateOf(false) }
            Box {
                AsyncImage(
                    model = serie.thumbnail,
                    contentDescription = "Serie Image",
                    modifier = Modifier
                        .padding(4.dp)
                        .height(100.dp)
                        .aspectRatio(1f),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    error = painterResource(id = R.drawable.baseline_warning_24),
                    onLoading = { isLoadedImage.value = false },
                    onSuccess = { isLoadedImage.value = true }
                )
                if (!isLoadedImage.value) CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .aspectRatio(1f)
                )
            }
            Column {
                Text(
                    text = serie.title,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = serie.description.trim(),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}