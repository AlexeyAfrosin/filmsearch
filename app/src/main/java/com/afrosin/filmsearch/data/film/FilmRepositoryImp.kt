package com.afrosin.filmsearch.data.film

import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.data.film.datasource.cache.CacheFilmDataSource
import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Observable

class FilmRepositoryImp(
    private val cloudFilmDataSource: FilmDataSource,
    private val cacheUserDataSource: CacheFilmDataSource
) : FilmRepository {

    override fun fetchFilms(): Observable<List<Film>> = cloudFilmDataSource
        .fetchFilms()
        .flatMap(::fetchFromCloudIfRequired)

    private fun fetchFromCloudIfRequired(films: List<Film>): Observable<List<Film>> =
        if (films.isEmpty()) {
            cloudFilmDataSource
                .fetchFilms()
                .flatMapSingle(cacheUserDataSource::retain)
        } else {
            Observable.just(films)
        }
}