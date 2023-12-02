package com.mz_dev.prueba_tecnica.data.model

import androidx.room.Entity

@Entity
data class Serie(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)
