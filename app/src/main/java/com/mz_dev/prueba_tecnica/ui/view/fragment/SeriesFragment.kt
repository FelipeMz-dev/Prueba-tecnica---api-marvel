package com.mz_dev.prueba_tecnica.ui.view.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mz_dev.prueba_tecnica.data.model.Serie
import com.mz_dev.prueba_tecnica.ui.view.component.card.SerieCard

@Composable
fun SeriesFragment(series: List<Serie>) {
    LazyColumn(
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ){
        items(series){
            SerieCard(it)
        }
    }
}