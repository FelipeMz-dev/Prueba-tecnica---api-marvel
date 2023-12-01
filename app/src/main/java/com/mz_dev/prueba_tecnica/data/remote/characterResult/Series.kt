package com.mz_dev.prueba_tecnica.data.remote.characterResult

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)