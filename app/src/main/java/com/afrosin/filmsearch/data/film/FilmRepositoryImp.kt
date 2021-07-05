package com.afrosin.filmsearch.data.film

import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.data.film.datasource.cache.CacheFilmDataSource
import com.afrosin.filmsearch.model.Film
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
}