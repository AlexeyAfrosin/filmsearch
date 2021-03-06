package com.afrosin.filmsearch.model

class RepositoryImpl : Repository {
    override fun getFilmsFromServer(): Film {
        return Film()
    }

    override fun getFilmsLocalStorageWorld(): List<Film> {
        return getFilmsWorld()
    }

    override fun getFilmsLocalStorageRus(): List<Film> {
        return getFilmsRus()
    }

}