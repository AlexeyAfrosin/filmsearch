package com.afrosin.filmsearch.di.module.network

import android.content.Context
import com.afrosin.filmsearch.data.api.FilmApi
import com.afrosin.filmsearch.data.api.FilmApiInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val IMAGE_URL = "https://image.tmdb.org/t/p/w300"

@Module
class NetworkModule {
    private val baseUrl = "https://api.tmdb.org/"


    private val gson: Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideFilmApi(context: Context): FilmApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(FilmApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FilmApi::class.java)
}