package com.afrosin.filmsearch

object FilmLibrary {
    val films = arrayListOf<Film>()

    fun randomFilm(): Film {
        val idx = (films.indices).random()
        return films[idx]
    }
}