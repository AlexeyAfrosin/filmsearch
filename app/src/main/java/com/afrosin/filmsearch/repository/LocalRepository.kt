package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmHistory

interface LocalRepository {
    fun getAllHistory(): List<FilmHistory>
    fun saveFilmHistoryEntity(filmHistory: FilmHistory)
}