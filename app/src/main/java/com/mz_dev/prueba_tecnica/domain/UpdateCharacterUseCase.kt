package com.mz_dev.prueba_tecnica.domain

import com.mz_dev.prueba_tecnica.data.MarvelRepository
import com.mz_dev.prueba_tecnica.data.model.Character
import javax.inject.Inject

class UpdateCharacterUseCase @Inject constructor (
    private val repository: MarvelRepository
){
    suspend operator fun invoke(character: Character) {
        repository.updateCharacter(character)
    }
}