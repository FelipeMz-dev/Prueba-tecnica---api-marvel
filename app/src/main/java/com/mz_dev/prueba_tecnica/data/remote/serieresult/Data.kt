package com.mz_dev.prueba_tecnica.data.remote.serieresult

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<SerieResult>,
    val total: Int
)