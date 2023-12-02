package com.mz_dev.prueba_tecnica.data.remote.serieresult

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)