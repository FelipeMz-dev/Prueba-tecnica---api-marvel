package com.mz_dev.prueba_tecnica.domain

import com.mz_dev.prueba_tecnica.data.MarvelRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    suspend operator fun invoke(quantity: Int) {
        repository.loadCharacters(quantity)
        repository.loadSeries(quantity)
    }
}