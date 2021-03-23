package com.afrosin.filmsearch.room

import androidx.room.*

@Dao
interface FilmViewHistoryDao {
    @Query("select * from FilmViewHistoryEntity order by id desc")
    fun all(): List<FilmViewHistoryEntity>

    @Query("select * from FilmViewHistoryEntity where filmId = :filmId order by id desc")
    fun getDataByFilmId(filmId: Long): List<FilmViewHistoryEntity>

    @Insert
    fun insert(filmViewHistoryEntity: FilmViewHistoryEntity)

    @Update
    fun update(filmViewHistoryEntity: FilmViewHistoryEntity)

    @Delete
    fun delete(filmViewHistoryEntity: FilmViewHistoryEntity)
}