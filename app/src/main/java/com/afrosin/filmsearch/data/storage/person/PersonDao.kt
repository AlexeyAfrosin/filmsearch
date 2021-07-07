package com.afrosin.filmsearch.data.storage.person

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrosin.filmsearch.model.Person
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePerson(persons: Person): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updatePersons(persons: List<Person>): Completable

    @Query("Select * from persons order by name")
    fun fetchPersons(): Observable<List<Person>>
}