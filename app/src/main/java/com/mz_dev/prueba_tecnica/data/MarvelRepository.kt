package com.mz_dev.prueba_tecnica.data

import com.mz_dev.prueba_tecnica.data.local.LocalCharacter
import com.mz_dev.prueba_tecnica.data.local.LocalDataSource
import com.mz_dev.prueba_tecnica.data.mapper.LocalToCharacterMapper
import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val characters: Flow<List<Character>> = localDataSource.characters.map { characters ->
        characters.map { LocalToCharacterMapper().map(it) }
    }

    suspend fun updateCharacter(character: Character) {
        localDataSource.updateCharacter(character)
    }

    suspend fun loadCharacters(limit: Int) {
        val isDbEmpty = localDataSource.countCharacters() == 0
        if (isDbEmpty) {
            localDataSource.insertAll(remoteDataSource.getCharacters(limit))
        }
    }
}
