package com.mz_dev.prueba_tecnica.data.remote.service

import com.mz_dev.prueba_tecnica.data.remote.characterresult.CharacterResult
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
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