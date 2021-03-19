package com.afrosin.filmsearch.utils

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmDTO
import com.afrosin.filmsearch.model.FilmsDiscoverDTO

fun convertFilmsDiscoverDtoToFilmList(filmsDiscoverDTO: FilmsDiscoverDTO): List<Film> {
    val filmsDTO: List<FilmDTO> = filmsDiscoverDTO.results!!
    val films = ArrayList<Film>()
    filmsDTO.forEach {
        films.add(Film(it.title, it.poster_path, it.overview))
    }
    return films
}
