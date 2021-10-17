package com.afrosin.filmsearch.data.storage

import androidx.room.RoomDatabase
import com.afrosin.filmsearch.data.storage.film.FilmDao
import com.afrosin.filmsearch.data.storage.person.PersonDao
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person

@androidx.room.Database(entities = [Film::class, Person::class], version = 1)
abstract class FilmStorage : RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun personDao(): PersonDao

}