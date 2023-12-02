package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.local.model.LocalSerie
import com.mz_dev.prueba_tecnica.data.model.Serie

class SerieToLocalMapper {
    fun map(serie: Serie) = LocalSerie(
        id = serie.id,
        title = serie.title,
        description = serie.description,
        thumbnail = serie.thumbnail
    )
}
