package com.mz_dev.prueba_tecnica.data.remote.service

import com.mz_dev.prueba_tecnica.data.remote.serieresult.SerieRemote
import com.mz_dev.prueba_tecnica.data.remote.serieresult.SerieResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SerieService {
    @GET("series")
    suspend fun getSeries(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): SerieRemote
}