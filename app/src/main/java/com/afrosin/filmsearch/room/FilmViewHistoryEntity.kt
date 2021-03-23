package com.afrosin.filmsearch.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmViewHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val filmId: Long,
    val note: String,
    val dateCreated: Long,
    val filmTitle: String
)
