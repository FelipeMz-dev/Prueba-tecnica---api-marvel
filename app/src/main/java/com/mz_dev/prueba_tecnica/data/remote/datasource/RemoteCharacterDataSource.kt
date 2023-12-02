package com.mz_dev.prueba_tecnica.data.remote.datasource

import com.mz_dev.prueba_tecnica.core.MarvelApiAuth
import com.mz_dev.prueba_tecnica.data.mapper.RemoteToCharacterMapper
import com.mz_dev.prueba_tecnica.data.remote.service.CharacterService
import javax.inject.Inject

class RemoteCharacterDataSource @Inject constructor(
    private val apiService: CharacterService
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