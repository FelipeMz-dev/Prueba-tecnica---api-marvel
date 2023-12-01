package com.mz_dev.prueba_tecnica.data.model

import androidx.room.Entity

@Entity
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val favorite: Boolean = false
)