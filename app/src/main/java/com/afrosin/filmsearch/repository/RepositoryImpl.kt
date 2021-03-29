package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.afrosin.filmsearch.model.getFilmsRus
import com.afrosin.filmsearch.model.getFilmsWorld
import retrofit2.Callback


class RepositoryImpl(private val remoteDataSource: RemoteDataSource = RemoteDataSource()) :
    Repository {

    override fun getFilmsFromServer(
        apiKey: String,
        language: String,
        callBack: Callback<FilmsDiscoverDTO>,

        ) {
        remoteDataSource.getFilmsFromServer(apiKey, language, callBack)
    }

    override fun getFilmsLocalStorageWorld() = getFilmsWorld()

    override fun getFilmsLocalStorageRus() = getFilmsRus()

}