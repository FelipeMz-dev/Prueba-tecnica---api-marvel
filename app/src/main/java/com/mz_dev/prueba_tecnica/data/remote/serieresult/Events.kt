package com.mz_dev.prueba_tecnica.data.remote.serieresult

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)