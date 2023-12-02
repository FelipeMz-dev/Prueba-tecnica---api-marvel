package com.mz_dev.prueba_tecnica.data.mapper

import com.mz_dev.prueba_tecnica.data.local.model.LocalSerie
import com.mz_dev.prueba_tecnica.data.model.Serie

class LocalToSerieMapper {
    fun map(localSerie: LocalSerie) = Serie(
        id = localSerie.id,
        title = localSerie.title,
        description = localSerie.description,
        thumbnail = localSerie.thumbnail
    )
}
