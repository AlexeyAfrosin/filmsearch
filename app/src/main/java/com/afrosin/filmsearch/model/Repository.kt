package com.afrosin.filmsearch.model

interface Repository {
    fun getFilmsFromServer(): Film
    fun getFilmsLocalStorage(): Film
}