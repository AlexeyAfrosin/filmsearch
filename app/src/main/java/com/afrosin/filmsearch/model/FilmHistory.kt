package com.afrosin.filmsearch.model

import java.util.*

data class FilmHistory(
    val id: Long,
    val filmId: Long,
    val note: String,
    val dateCreated: Date,
    val filmTitle: String
)