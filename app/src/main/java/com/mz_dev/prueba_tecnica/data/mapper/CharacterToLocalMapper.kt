package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.local.model.LocalCharacter
import com.mz_dev.prueba_tecnica.data.model.Character

class CharacterToLocalMapper {
    fun map(character: Character) = LocalCharacter(
        id = character.id,
        name = character.name,
        description = character.description,
        thumbnail = character.thumbnail,
        favorite = character.favorite
    )
}