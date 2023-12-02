package com.mz_dev.prueba_tecnica.data.local.datasource

import com.mz_dev.prueba_tecnica.data.local.MarvelDao
import com.mz_dev.prueba_tecnica.data.local.model.LocalCharacter
import com.mz_dev.prueba_tecnica.data.mapper.CharacterToLocalMapper
import com.mz_dev.prueba_tecnica.data.model.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCharacterDataSource @Inject constructor(
    private val dao: MarvelDao
) {
    val characters: Flow<List<LocalCharacter>> = dao.getCharacters()

    suspend fun updateCharacter(character: Character) {
        dao.updateCharacter(CharacterToLocalMapper().map(character))
    }

    suspend fun insertAll(characters: List<Character>) {
        dao.insertAllCharacters(characters.map { CharacterToLocalMapper().map(it)})
    }

    suspend fun countCharacters(): Int {
        return dao.countCharacters()
    }
}