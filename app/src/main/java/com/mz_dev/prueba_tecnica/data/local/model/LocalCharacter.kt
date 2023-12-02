package com.mz_dev.prueba_tecnica.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalCharacter(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val favorite: Boolean = false
)