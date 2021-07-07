package com.afrosin.filmsearch.data.film.datasource.cache

import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Single

interface CacheFilmDataSource : FilmDataSource {
    fun retain(films: List<Film>, lang: String, includeAdult: Boolean): Single<List<Film>>
    fun retainPopularPersons(persons: List<Person>): Single<List<Person>>
}
