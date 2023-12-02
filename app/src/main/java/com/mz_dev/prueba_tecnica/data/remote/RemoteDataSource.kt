package com.mz_dev.prueba_tecnica.data.remote

import com.mz_dev.prueba_tecnica.core.MarvelApiAuth
import com.mz_dev.prueba_tecnica.data.mapper.RemoteToCharacterMapper
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MarvelService
) {
    private val auth = MarvelApiAuth()
    private val apiKey = auth.getPublicApiKey()
    private val timestamp = auth.generateTimestamp()
    private val hash = auth.generateHash(timestamp)
    private val orderBy = "-modified"

    suspend fun getCharacters(limit: Int, offset: Int) = apiService
        .getCharacters(apiKey, timestamp, hash, orderBy,limit, offset)
        .data
        .results
        .map { RemoteToCharacterMapper().map(it) }
}