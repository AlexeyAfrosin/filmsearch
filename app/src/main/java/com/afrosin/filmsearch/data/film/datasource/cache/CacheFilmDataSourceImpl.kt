package com.afrosin.filmsearch.data.film.datasource.cache

import com.afrosin.filmsearch.data.storage.FilmStorage
import com.afrosin.filmsearch.data.storage.film.FilmDao
import com.afrosin.filmsearch.data.storage.person.PersonDao
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CacheFilmDataSourceImpl(filmStorage: FilmStorage) : CacheFilmDataSource {

    private val filmDao: FilmDao = filmStorage.filmDao()
    private val personDao: PersonDao = filmStorage.personDao()

    override fun retain(
        films: List<Film>,
        lang: String,
        includeAdult: Boolean
    ): Single<List<Film>> =
        filmDao
            .updateFilms(films)
            .andThen(fetchFilms(lang, includeAdult))
            .firstOrError()

    override fun fetchFilms(lang: String, includeAdult: Boolean): Observable<List<Film>> =
        filmDao.fetchFilms()

    override fun retainPopularPersons(persons: List<Person>): Single<List<Person>> =
        personDao
            .updatePersons(persons)
            .andThen(fetchPopularPersons())
            .firstOrError()

    override fun fetchPopularPersons(): Observable<List<Person>> =
        personDao.fetchPersons()
}