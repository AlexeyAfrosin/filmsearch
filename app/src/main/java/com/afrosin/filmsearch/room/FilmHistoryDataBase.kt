package com.afrosin.filmsearch.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FilmViewHistoryEntity::class), version = 1, exportSchema = true)

abstract class FilmHistoryDataBase : RoomDatabase() {
    abstract fun filmViewHistoryDao(): FilmViewHistoryDao
}