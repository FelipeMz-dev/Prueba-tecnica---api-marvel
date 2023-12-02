package com.mz_dev.prueba_tecnica.data.remote.characterresult

data class RemoteCharacter(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>,
    val favorite: Boolean = false
)