package com.afrosin.filmsearch.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val FILM_ID = "filmId"
const val NOTE = "note"
const val DATE_CREATED = "dateCreated"
const val FILM_TITLE = "filmTitle"

@Entity
data class FilmViewHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val filmId: Long,
    val note: String,
    val dateCreated: Long,
    val filmTitle: String
)
