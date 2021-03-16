package com.afrosin.filmsearch.model

interface Repository {
    fun getFilmsFromServer(): Film
    fun getFilmsLocalStorageWorld(): List<Film>
    fun getFilmsLocalStorageRus(): List<Film>
}