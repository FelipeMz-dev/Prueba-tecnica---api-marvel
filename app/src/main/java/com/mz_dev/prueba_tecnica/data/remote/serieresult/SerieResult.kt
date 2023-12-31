package com.mz_dev.prueba_tecnica.data.remote.serieresult

data class SerieResult(
    val characters: Characters,
    val comics: Comics,
    val creators: Creators,
    val description: Any,
    val endYear: Int,
    val events: Events,
    val id: Int,
    val modified: String,
    val next: Any,
    val previous: Any,
    val rating: String,
    val resourceURI: String,
    val startYear: Int,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val title: String,
    val type: String,
    val urls: List<Url>
)