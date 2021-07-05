package com.afrosin.filmsearch.data.film.datasource.cloud

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import io.reactivex.rxjava3.core.Single

object CloudFilmMapper {
    fun map(filmsDiscoverDTO: FilmsDiscoverDTO): Single<List<Film>> {
        return Single.just(filmsDiscoverDTO.results)
    }
}