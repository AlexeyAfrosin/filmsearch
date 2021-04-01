package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.afrosin.filmsearch.model.FilmsPersonPopularDTO
import com.afrosin.filmsearch.model.PersonDetailsDTO
import retrofit2.Callback


interface Repository {
    fun getFilmsFromServer(
        apiKey: String, language: String, callBack: Callback<FilmsDiscoverDTO>,
        includeAdult: Boolean
    )

    fun getFilmsLocalStorageWorld(): List<Film>
    fun getFilmsLocalStorageRus(): List<Film>

    fun getPopularPersonFromServer(
        apiKey: String,
        language: String,
        callBack: Callback<FilmsPersonPopularDTO>
    )

    fun getPersonDetailsFromServer(
        person_id: Long,
        apiKey: String,
        language: String,
        callBack: Callback<PersonDetailsDTO>
    )
}