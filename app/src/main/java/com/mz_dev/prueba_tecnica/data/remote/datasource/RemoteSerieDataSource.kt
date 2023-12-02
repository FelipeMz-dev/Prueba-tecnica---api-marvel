package com.mz_dev.prueba_tecnica.data.remote.datasource

import com.mz_dev.prueba_tecnica.core.MarvelApiAuth
import com.mz_dev.prueba_tecnica.data.mapper.RemoteToSerieMapper
import com.mz_dev.prueba_tecnica.data.remote.service.SerieService
import javax.inject.Inject

class RemoteSerieDataSource @Inject constructor(
    private val apiService: SerieService
) {
    private val auth = MarvelApiAuth()
    private val apiKey = auth.getPublicApiKey()
    private val timestamp = auth.generateTimestamp()
    private val hash = auth.generateHash(timestamp)
    private val orderBy = "-modified"

    suspend fun getSeries(limit: Int, offset: Int) = apiService
        .getSeries(apiKey, timestamp, hash, orderBy,limit, offset)
        .data
        .results
        .map { RemoteToSerieMapper().map(it) }
}