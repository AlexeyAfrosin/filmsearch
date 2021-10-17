package com.afrosin.filmsearch.di.module.film


import android.content.Context
import androidx.room.Room
import com.afrosin.filmsearch.data.api.FilmApi
import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.data.film.FilmRepositoryImp
import com.afrosin.filmsearch.data.film.datasource.FilmDataSource
import com.afrosin.filmsearch.data.film.datasource.cache.CacheFilmDataSource
import com.afrosin.filmsearch.data.film.datasource.cache.CacheFilmDataSourceImpl
import com.afrosin.filmsearch.data.film.datasource.cloud.CloudFilmDataSource
import com.afrosin.filmsearch.data.storage.FilmStorage
import com.afrosin.filmsearch.di.module.Cache
import com.afrosin.filmsearch.di.module.Cloud
import com.afrosin.filmsearch.di.module.InMemory
import com.afrosin.filmsearch.di.module.Persisted
import com.afrosin.filmsearch.di.module.network.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [FilmDiModule::class, NetworkModule::class])
class FilmModule {
    @Singleton
    @Provides
    fun providerFilmRepository(
        @Cloud cloudUserDataSource: FilmDataSource,
        @Cache cacheUserDataSource: CacheFilmDataSource
    ): FilmRepository = FilmRepositoryImp(
        cloudUserDataSource,
        cacheUserDataSource
    )

    @Cloud
    @Singleton
    @Provides
    fun provideCloudFilmDataSource(filmApi: FilmApi): FilmDataSource =
        CloudFilmDataSource(filmApi)


    @Cache
    @Singleton
    @Provides
    fun provideCacheFilmDataSource(@Persisted filmStorage: FilmStorage): CacheFilmDataSource =
        CacheFilmDataSourceImpl(filmStorage)

    @InMemory
    @Singleton
    @Provides
    fun provideInMemoryFilmStorage(context: Context): FilmStorage =
        Room.inMemoryDatabaseBuilder(context, FilmStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()

    @Persisted
    @Singleton
    @Provides
    fun provideDatabaseFilmStorage(context: Context): FilmStorage =
        Room.databaseBuilder(context, FilmStorage::class.java, "films.db")
            .build()
}
