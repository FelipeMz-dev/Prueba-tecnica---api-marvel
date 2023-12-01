package com.mz_dev.prueba_tecnica.data.local

import com.mz_dev.prueba_tecnica.data.mapper.CharacterToLocalMapper
import com.mz_dev.prueba_tecnica.data.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: MarvelDao
) {
    val characters: Flow<List<LocalCharacter>> = dao.getCharacters()

    suspend fun updateCharacter(character: Character) {
        dao.updateCharacter(CharacterToLocalMapper().map(character))
    }

    suspend fun insertAll(characters: List<Character>) {
        dao.insertAll(characters.map { CharacterToLocalMapper().map(it)})
    }

    suspend fun countCharacters(): Int {
        return dao.countCharacters()
    }
}