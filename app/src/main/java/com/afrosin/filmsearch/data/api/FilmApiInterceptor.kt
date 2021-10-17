package com.afrosin.filmsearch.data.api

import com.afrosin.filmsearch.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


object FilmApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl =
            request.url.newBuilder().addQueryParameter("api_key", BuildConfig.FILM_API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
