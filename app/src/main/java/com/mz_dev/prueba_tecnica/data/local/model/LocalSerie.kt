package com.mz_dev.prueba_tecnica.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalSerie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)