package com.afrosin.filmsearch.repository

import com.afrosin.filmsearch.model.FilmsDiscoverDTO
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.tmdb.org/"
const val POSTER_URL = "https://image.tmdb.org/t/p/w300"

class RemoteDataSource {

    private val filmsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createOkHttpClient(FilmsApiInterceptor()))
        .build()
        .create(FilmsAPI::class.java)

    fun getFilmsFromServer(apiKey: String, language: String, callBack: Callback<FilmsDiscoverDTO>) {
        filmsApi.getDiscoverMovie(apiKey, language)
            .enqueue(callBack)
    }


    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class FilmsApiInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }

    }
}