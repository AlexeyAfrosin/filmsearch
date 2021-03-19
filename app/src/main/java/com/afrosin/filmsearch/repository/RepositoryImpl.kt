package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.afrosin.filmsearch.model.getFilmsRus
import com.afrosin.filmsearch.model.getFilmsWorld
import retrofit2.Callback


class RepositoryImpl : Repository {

    override fun getFilmsFromServer(
        apiKey: String,
        language: String,
        callBack: Callback<FilmsDiscoverDTO>
    ) {
        val remoteDataSource = RemoteDataSource()
        remoteDataSource.getFilmsFromServer(apiKey, language, callBack)
    }

    override fun getFilmsLocalStorageWorld() = getFilmsWorld()

    override fun getFilmsLocalStorageRus() = getFilmsRus()

}