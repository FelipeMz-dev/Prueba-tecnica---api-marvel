package com.mz_dev.prueba_tecnica.data

import com.mz_dev.prueba_tecnica.data.local.datasource.LocalCharacterDataSource
import com.mz_dev.prueba_tecnica.data.local.datasource.LocalSeriesDataSource
import com.mz_dev.prueba_tecnica.data.mapper.LocalToCharacterMapper
import com.mz_dev.prueba_tecnica.data.mapper.LocalToSerieMapper
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.data.model.Serie
import com.mz_dev.prueba_tecnica.data.remote.datasource.RemoteCharacterDataSource
import com.mz_dev.prueba_tecnica.data.remote.datasource.RemoteSerieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val localDataSource: LocalCharacterDataSource,
    private val remoteCharacterSource: RemoteCharacterDataSource,
    private val remoteSerieSource: RemoteSerieDataSource,
    private val localSeriesDataSource: LocalSeriesDataSource
) {
    val characters: Flow<List<Character>> = localDataSource.characters.map { characters ->
        characters.map { LocalToCharacterMapper().map(it) }
    }

    val series: Flow<List<Serie>> = localSeriesDataSource.series.map { series ->
        series.map { LocalToSerieMapper().map(it) }
    }

    suspend fun updateCharacter(character: Character) {
        localDataSource.updateCharacter(character)
    }

    suspend fun loadSeries(quantity: Int) {
        val isDbEmpty = localSeriesDataSource.countSeries() == 0
        if (isDbEmpty) {
            val series = mutableListOf<Serie>()
            var offset = 0
            var limit = 20
            while (offset < quantity) {
                remoteSerieSource.getSeries(limit, offset).let { newSeries ->
                    series.addAll(newSeries)
                }
                offset += limit
                if (limit + offset > quantity) limit = quantity - offset
            }
            localSeriesDataSource.insertAll(series)
        }
    }

    suspend fun loadCharacters(quantity: Int) {
        val isDbEmpty = localDataSource.countCharacters() == 0
        if (isDbEmpty) {
            val characters = mutableListOf<Character>()
            var offset = 0
            var limit = 20
            while (offset < quantity) {
                remoteCharacterSource.getCharacters(limit, offset + 74).let { newCharacters ->
                    characters.addAll(newCharacters)
                }
                offset += limit
                if (limit + offset > quantity) limit = quantity - offset
            }
            localDataSource.insertAll(characters)
        }
    }
}

