package com.afrosin.filmsearch.model

class RepositoryImpl : Repository {
    override fun getFilmsFromServer(): Film {
        return Film()
    }

    override fun getFilmsLocalStorage(): Film {
        return Film()
    }
}