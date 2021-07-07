package com.afrosin.filmsearch.data.film.datasource.cloud

import com.afrosin.filmsearch.model.FilmsPersonPopularDTO
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Single

object CloudPersonMapper {
    fun map(filmsPersonPopularDTO: FilmsPersonPopularDTO): Single<List<Person>> {
        return Single.just(filmsPersonPopularDTO.results)
    }
}