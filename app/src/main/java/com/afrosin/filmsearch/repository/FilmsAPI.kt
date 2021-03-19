package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsAPI {
    @GET("3/discover/movie")
    fun getDiscoverMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<FilmsDiscoverDTO>
}