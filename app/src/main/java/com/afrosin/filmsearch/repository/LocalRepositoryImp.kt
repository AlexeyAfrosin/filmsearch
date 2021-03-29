package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.room.FilmViewHistoryDao
import com.afrosin.filmsearch.utils.convertFilmHistoryEntityToFilmHistory
import com.afrosin.filmsearch.utils.convertFilmHistoryToFilmHistoryEntity

class LocalRepositoryImp(private val localDataSource: FilmViewHistoryDao) : LocalRepository {

    override fun getAllHistory(): List<FilmHistory> {
        return convertFilmHistoryEntityToFilmHistory(localDataSource.all())
    }

    override fun saveFilmHistoryEntity(filmHistory: FilmHistory) {
        localDataSource.insert(convertFilmHistoryToFilmHistoryEntity(filmHistory))
    }
}