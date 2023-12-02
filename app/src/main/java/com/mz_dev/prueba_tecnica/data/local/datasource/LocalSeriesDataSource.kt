package com.mz_dev.prueba_tecnica.data.local.datasource

import com.mz_dev.prueba_tecnica.data.local.MarvelDao
import com.mz_dev.prueba_tecnica.data.local.model.LocalSerie
import com.mz_dev.prueba_tecnica.data.mapper.SerieToLocalMapper
import com.mz_dev.prueba_tecnica.data.model.Serie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSeriesDataSource @Inject constructor(
    private val dao: MarvelDao
) {
    val series: Flow<List<LocalSerie>> = dao.getSeries()

    suspend fun insertAll(series: List<Serie>) {
        dao.insertAllSeries(series.map {
            println(it.title)
            SerieToLocalMapper().map(it)
        })
    }

    suspend fun countSeries(): Int {
        return dao.countSeries()
    }
}