package com.afrosin.filmsearch.data.film.datasource.cloud

import com.afrosin.filmsearch.model.DiscoverFilmDTO
import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Single

object CloudFilmMapper {
    fun map(filmsDiscoverDTO: DiscoverFilmDTO): Single<List<Film>> {
        return Single.just(filmsDiscoverDTO.results)
    }
}