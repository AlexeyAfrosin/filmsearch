package com.afrosin.filmsearch.utils

import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.room.FilmViewHistoryEntity
import java.util.*

fun convertFilmHistoryEntityToFilmHistory(filmHistoryEntity: List<FilmViewHistoryEntity>): List<FilmHistory> {
    return filmHistoryEntity.map {
        FilmHistory(it.id, it.filmId, it.note, Date(it.dateCreated), it.filmTitle)
    }
}

fun convertFilmHistoryToFilmHistoryEntity(filmHistory: FilmHistory): FilmViewHistoryEntity {
    return FilmViewHistoryEntity(
        0,
        filmHistory.filmId,
        filmHistory.note,
        filmHistory.dateCreated.time,
        filmHistory.filmTitle
    )
}