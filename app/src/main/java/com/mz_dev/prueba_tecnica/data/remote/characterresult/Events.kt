package com.mz_dev.prueba_tecnica.data.remote.characterresult

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)