package com.mz_dev.prueba_tecnica.domain

import com.mz_dev.prueba_tecnica.data.MarvelRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke() = repository.characters
}