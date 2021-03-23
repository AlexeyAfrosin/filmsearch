package com.afrosin.filmsearch.app

import android.app.Application
import androidx.room.Room
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.room.FilmHistoryDataBase
import com.afrosin.filmsearch.room.FilmViewHistoryDao

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: FilmHistoryDataBase? = null
        private const val DB_NAME = "FilmHistory.db"

        fun getFilmViewHistoryDao(): FilmViewHistoryDao {
            if (db == null) {
                synchronized(FilmHistoryDataBase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException(
                            appInstance!!.applicationContext.getString(
                                R.string.error_creating_database
                            )
                        )

                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FilmHistoryDataBase::class.java,
                            DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return db!!.filmViewHistoryDao()
        }
    }
}