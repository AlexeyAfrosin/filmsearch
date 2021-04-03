package com.afrosin.filmsearch.utils

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmDTO
import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.afrosin.filmsearch.room.FilmViewHistoryEntity
import java.util.*
import kotlin.collections.ArrayList

fun convertFilmsDiscoverDtoToFilmList(filmsDiscoverDTO: FilmsDiscoverDTO): List<Film> {
    val filmsDTO: List<FilmDTO> = filmsDiscoverDTO.results!!
    val films = ArrayList<Film>()
    filmsDTO.forEach {
        films.add(Film(it.title, it.poster_path, it.overview, it.id))
    }
    return films
}

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