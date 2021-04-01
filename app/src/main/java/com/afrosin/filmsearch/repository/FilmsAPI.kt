package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.afrosin.filmsearch.model.FilmsPersonPopularDTO
import com.afrosin.filmsearch.model.PersonDetailsDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsAPI {
    @GET("3/discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("include_adult") includeAdult: Boolean
    ): Call<FilmsDiscoverDTO>

    @GET("3/person/popular")
    fun getPersonPopular(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<FilmsPersonPopularDTO>

    @GET("3/person/{id}")
    fun getPersonDetails(
        @Path("id") person_id: Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<PersonDetailsDTO>
}