package com.afrosin.filmsearch.model

class RepositoryImpl : Repository {
    override fun getFilmsFromServer() = Film()

    override fun getFilmsLocalStorageWorld() = getFilmsWorld()

    override fun getFilmsLocalStorageRus() = getFilmsRus()

}