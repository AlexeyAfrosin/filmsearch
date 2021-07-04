package com.afrosin.filmsearch.data.storage

import androidx.room.RoomDatabase
import com.afrosin.filmsearch.data.storage.film.FilmDao
import com.afrosin.filmsearch.model.Film

@androidx.room.Database(entities = [Film::class], version = 1)
abstract class FilmStorage : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}