package com.afrosin.filmsearch.data.film.datasource.cache

import com.afrosin.filmsearch.data.storage.FilmStorage
import com.afrosin.filmsearch.data.storage.film.FilmDao
import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class CacheFilmDataSourceImpl(filmStorage: FilmStorage) : CacheFilmDataSource {

    private val filmDao: FilmDao = filmStorage.filmDao()

    override fun retain(films: List<Film>): Single<List<Film>> =
        filmDao
            .updateFilms(films)
            .andThen(fetchFilms())
            .firstOrError()

    override fun fetchFilms(): Observable<List<Film>> = filmDao.fetchFilms()
}