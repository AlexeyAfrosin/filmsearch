package com.afrosin.filmsearch.data.film.datasource.cloud

import com.afrosin.filmsearch.data.api.FilmApi
import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class CloudFilmDataSource(private val filmApi: FilmApi) : FilmDataSource {

    override fun fetchFilms(lang: String, includeAdult: Boolean): Observable<List<Film>> =
        filmApi
            .getDiscoverMovie(language = lang, includeAdult = includeAdult)
            .map { it.results }
            .delay(1L, TimeUnit.SECONDS)
            .toObservable()

    override fun fetchPopularPersons(): Observable<List<Person>> =
        filmApi
            .getPersonPopular()
            .map { it.results }
            .delay(1L, TimeUnit.SECONDS)
            .toObservable()
}