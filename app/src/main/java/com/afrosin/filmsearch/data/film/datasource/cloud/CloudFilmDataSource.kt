package com.afrosin.filmsearch.data.film.datasource.cloud

import com.afrosin.filmsearch.data.api.FilmApi
import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class CloudFilmDataSource(private val filmApi: FilmApi) : FilmDataSource {

    override fun fetchFilms(): Observable<List<Film>> =
        filmApi
            .getDiscoverMovie()
            .delay(1L, TimeUnit.SECONDS)
            .toObservable()
}