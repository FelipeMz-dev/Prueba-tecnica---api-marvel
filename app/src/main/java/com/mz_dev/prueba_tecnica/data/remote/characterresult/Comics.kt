package com.mz_dev.prueba_tecnica.data.remote.characterresult

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)