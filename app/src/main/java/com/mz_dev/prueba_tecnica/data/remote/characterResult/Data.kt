package com.mz_dev.prueba_tecnica.data.remote.characterResult

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<RemoteCharacter>,
    val total: Int
)