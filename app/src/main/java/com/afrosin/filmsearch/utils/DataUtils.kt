package com.afrosin.filmsearch.utils

import com.afrosin.filmsearch.model.*
import com.afrosin.filmsearch.room.FilmViewHistoryEntity
import java.util.*
import kotlin.collections.ArrayList

//fun convertFilmsDiscoverDtoToFilmList(discoverFilm: DiscoverFilm): List<Film> {
//    val filmsDTO: List<Film> = discoverFilm.results!!
//    val films = ArrayList<Film>()
//    filmsDTO.forEach {
//        films.add(Film(it.title, it.poster_path, it.overview, it.id))
//    }
//    return films
//}

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

fun converFilmsPersonPopularDtoToFilmsPersonPopularList(filmsPersonPopularDTO: FilmsPersonPopularDTO): List<Person> {
    val personDTO: List<PersonDTO> = filmsPersonPopularDTO.results!!
    val persons = ArrayList<Person>()
    personDTO.forEach {
        persons.add(Person(it.profile_path, it.name, it.id))
    }
    return persons
}

fun converPersonDetailsDtoToPersonDetails(personDetailsDTO: PersonDetailsDTO): PersonDetails {
    return PersonDetails(personDetailsDTO.id, personDetailsDTO.place_of_birth)
}
