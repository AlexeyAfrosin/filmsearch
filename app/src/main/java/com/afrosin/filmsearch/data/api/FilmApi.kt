package com.afrosin.filmsearch.data.api

import com.afrosin.filmsearch.BuildConfig
import com.afrosin.filmsearch.model.DiscoverFilmDTO
import com.afrosin.filmsearch.model.FilmsPersonPopularDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {
    @GET("3/discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") api_key: String? = BuildConfig.FILM_API_KEY,
        @Query("language") language: String? = "ru-RU",
        @Query("include_adult") includeAdult: Boolean? = false
    ): Single<DiscoverFilmDTO>

    @GET("3/person/popular")
    fun getPersonPopular(
        @Query("api_key") api_key: String? = BuildConfig.FILM_API_KEY,
        @Query("language") language: String? = "ru-RU",
    ): Single<FilmsPersonPopularDTO>

}