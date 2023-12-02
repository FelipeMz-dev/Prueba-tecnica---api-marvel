package com.mz_dev.prueba_tecnica.data.remote

import com.mz_dev.prueba_tecnica.data.remote.characterResult.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharacterResult
}