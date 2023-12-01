package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.local.LocalCharacter
import com.mz_dev.prueba_tecnica.data.model.Character

class LocalToCharacterMapper {
    fun map(localCharacter: LocalCharacter) = Character(
        id = localCharacter.id,
        name = localCharacter.name,
        description = localCharacter.description,
        thumbnail = localCharacter.thumbnail,
        favorite = localCharacter.favorite
    )
}