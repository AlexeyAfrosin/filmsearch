package com.afrosin.filmsearch.data.storage.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrosin.filmsearch.model.Film
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilm(film: Film): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateFilms(films: List<Film>): Completable

    @Query("Select * from films order by title")
    fun fetchFilms(): Observable<List<Film>>
}