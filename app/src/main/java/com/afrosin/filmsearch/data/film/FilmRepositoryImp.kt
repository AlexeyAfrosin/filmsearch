package com.afrosin.filmsearch.data.film

import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.data.film.datasource.cache.CacheFilmDataSource
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Observable

class FilmRepositoryImp(
    private val cloudFilmDataSource: FilmDataSource,
    private val cacheUserDataSource: CacheFilmDataSource
) : FilmRepository {

    override fun fetchFilms(lang: String, includeAdult: Boolean): Observable<List<Film>> =
        cloudFilmDataSource
            .fetchFilms(lang, includeAdult)
            .flatMap { fetchFromCloudIfRequired(it, lang, includeAdult) }

    private fun fetchFromCloudIfRequired(
        films: List<Film>,
        lang: String,
        includeAdult: Boolean
    ): Observable<List<Film>> =
        if (films.isEmpty()) {
            cloudFilmDataSource
                .fetchFilms(lang, includeAdult)
                .flatMapSingle { cacheUserDataSource.retain(it, lang, includeAdult) }
        } else {
            Observable.just(films)
        }

    override fun fetchPopularPersons(): Observable<List<Person>> =
        cloudFilmDataSource
            .fetchPopularPersons()
            .flatMap(::fetchPopularPersonsFromCloudIfRequired)


    private fun fetchPopularPersonsFromCloudIfRequired(
        persons: List<Person>
    ): Observable<List<Person>> =
        if (persons.isEmpty()) {
            cloudFilmDataSource
                .fetchPopularPersons()
                .flatMapSingle { cacheUserDataSource.retainPopularPersons(it) }
        } else {
            Observable.just(persons)
        }
}