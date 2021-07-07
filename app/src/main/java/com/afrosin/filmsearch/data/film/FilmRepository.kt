package com.afrosin.filmsearch.data.film

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Observable

interface FilmRepository {
    /**
     * Возвращает список фильмов
     */
    fun fetchFilms(lang: String, includeAdult: Boolean): Observable<List<Film>>

    /**
     * Возвращает список популярных актеров
     */
    fun fetchPopularPersons(): Observable<List<Person>>
}