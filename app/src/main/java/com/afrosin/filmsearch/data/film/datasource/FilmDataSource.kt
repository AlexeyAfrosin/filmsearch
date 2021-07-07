package com.afrosin.filmsearch.data.film.datasource

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Observable

interface FilmDataSource {
    fun fetchFilms(lang: String, includeAdult: Boolean): Observable<List<Film>>
    fun fetchPopularPersons(): Observable<List<Person>>
}