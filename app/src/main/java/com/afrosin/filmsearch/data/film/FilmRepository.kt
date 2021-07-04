package com.afrosin.filmsearch.data.film

import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Observable

interface FilmRepository {
    /**
     * Возвращает список фильмов
     */
    fun fetchFilms(): Observable<List<Film>>
}