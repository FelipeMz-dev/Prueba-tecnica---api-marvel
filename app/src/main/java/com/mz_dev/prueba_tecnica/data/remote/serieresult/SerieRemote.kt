package com.mz_dev.prueba_tecnica.data.remote.serieresult

data class SerieRemote(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)