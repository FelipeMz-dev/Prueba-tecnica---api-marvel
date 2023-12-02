package com.mz_dev.prueba_tecnica.core.di

import android.content.Context
import androidx.room.Room
import com.mz_dev.prueba_tecnica.data.local.MarvelDao
import com.mz_dev.prueba_tecnica.data.local.MarvelDatabase
import com.mz_dev.prueba_tecnica.data.remote.service.CharacterService
import com.mz_dev.prueba_tecnica.data.remote.service.SerieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    //Retrofit inject
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    @Singleton
    @Provides
    fun provideMarelService(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCharacterMarvelApiService(retrofit: Retrofit): CharacterService = retrofit.create(
        CharacterService::class.java
    )

    @Singleton
    @Provides
    fun provideSerieMarvelApiService(retrofit: Retrofit): SerieService = retrofit.create(
        SerieService::class.java
    )

    //Room inject
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MarvelDatabase {
        return Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            "marvel_database"
        ).build()
    }

    @Provides
    fun provideMarvelDao(database: MarvelDatabase): MarvelDao {
        return database.getDao()
    }
}