package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.model.Serie
import com.mz_dev.prueba_tecnica.data.remote.serieresult.SerieResult

class RemoteToSerieMapper {
    fun map(remoteSerie: SerieResult) = Serie(
        id = 0,
        title = remoteSerie.title,
        description = "",
        thumbnail =
        "${remoteSerie.thumbnail.path}.${remoteSerie.thumbnail.extension}"
    )
}