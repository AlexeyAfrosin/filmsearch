package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.*
import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource = RemoteDataSource()) :
    Repository {

    override fun getFilmsFromServer(
        apiKey: String,
        language: String,
        callBack: Callback<FilmsDiscoverDTO>,
        includeAdult: Boolean
    ) {
        remoteDataSource.getFilmsFromServer(apiKey, language, callBack, includeAdult)
    }

    override fun getFilmsLocalStorageWorld() = getFilmsWorld()

    override fun getFilmsLocalStorageRus() = getFilmsRus()

    override fun getPopularPersonFromServer(
        apiKey: String,
        language: String,
        callBack: Callback<FilmsPersonPopularDTO>
    ) {
        remoteDataSource.getPopularPersonsFromServer(apiKey, language, callBack)
    }

    override fun getPersonDetailsFromServer(
        person_id: Long,
        apiKey: String,
        language: String,
        callBack: Callback<PersonDetailsDTO>
    ) {
        remoteDataSource.getPersonDetailsFromServer(person_id, apiKey, language, callBack)
    }

}