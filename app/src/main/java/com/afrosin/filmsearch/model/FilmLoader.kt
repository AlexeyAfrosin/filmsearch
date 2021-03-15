package com.afrosin.filmsearch.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import com.afrosin.filmsearch.BuildConfig
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


class FilmLoader(private val listener: FilmLoaderListener, private val isRussian: Boolean) {
    private val FILM_API_URL = "https://api.tmdb.org/3/discover/movie"

    interface FilmLoaderListener {
        fun onLoaded(films: List<Film>)
        fun onFailed(throwable: Throwable)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilms() {

        val lang: String = when (isRussian) {
            true -> "ru-RU"
            else -> "en-EN"
        }
        val url = "${FILM_API_URL}?api_key=${BuildConfig.FILM_API_KEY}&language=${lang}"

        try {

            val uri = URL(url)
            val handler = Handler(Looper.getMainLooper())

            Thread {
                var urlConnection: HttpsURLConnection? = null
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val reader =
                        BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                    val filmsJSON = getLines(reader)
                    val filmsType = object : TypeToken<List<Film>>() {}.type
                    val filmsStr = Gson().fromJson(filmsJSON, JsonObject::class.java)
                    val films: List<Film> =
                        Gson().fromJson(filmsStr.get("results"), filmsType)

                    handler.post {
                        listener.onLoaded(films)
                    }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    handler.post {
                        listener.onFailed(e)
                    }
                } finally {
                    urlConnection?.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))

    }
}