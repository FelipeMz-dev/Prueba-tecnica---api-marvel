package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.model.Character
import com.mz_dev.prueba_tecnica.data.remote.characterResult.RemoteCharacter

class RemoteToCharacterMapper {
    fun map(remoteCharacter: RemoteCharacter) = Character(
        id = 0,
        name = remoteCharacter.name,
        description = remoteCharacter.description,
        thumbnail =
        "${remoteCharacter.thumbnail.path}.${remoteCharacter.thumbnail.extension}",
        favorite = false
    )
}