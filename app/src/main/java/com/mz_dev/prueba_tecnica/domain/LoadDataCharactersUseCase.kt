package com.mz_dev.prueba_tecnica.domain

import com.mz_dev.prueba_tecnica.data.MarvelRepository
import javax.inject.Inject

class LoadDataCharactersUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(limit: Int) {
        repository.loadCharacters(limit)
    }

}