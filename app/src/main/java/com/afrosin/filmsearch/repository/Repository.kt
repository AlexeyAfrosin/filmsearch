package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import retrofit2.Callback


interface Repository {
    fun getFilmsFromServer(
        apiKey: String, language: String, callBack: Callback<FilmsDiscoverDTO>,
        includeAdult: Boolean
    )

    fun getFilmsLocalStorageWorld(): List<Film>
    fun getFilmsLocalStorageRus(): List<Film>
}