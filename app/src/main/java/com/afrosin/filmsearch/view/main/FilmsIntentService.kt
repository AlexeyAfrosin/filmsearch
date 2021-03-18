package com.afrosin.filmsearch.view.main

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.afrosin.filmsearch.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

const val IS_RUSSIAN_LANG_EXTRA = "IS_RUSSIAN_EXTRA"
const val REQUEST_GET = "GET"
const val REQUEST_READ_TIMEOUT = 10000

class FilmsIntentService(name: String = "FilmsService") : IntentService(name) {
    private val FILM_API_URL = "https://api.tmdb.org/3/discover/movie"
    private val broadcastIntent = Intent(FILMS_INTENT_FILTER)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            onEmptyIntent()
        } else {
            val isRussian: Boolean = intent.getBooleanExtra(IS_RUSSIAN_LANG_EXTRA, true)
            loadFilms(isRussian)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadFilms(isRussian: Boolean) {

        val lang: String = when (isRussian) {
            true -> "ru-RU"
            else -> "en-EN"
        }
        val url = "${FILM_API_URL}?api_key=${BuildConfig.FILM_API_KEY}&language=${lang}"

        try {

            val uri = URL(url)

            var urlConnection: HttpsURLConnection? = null
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_READ_TIMEOUT
                }

                val reader =
                    BufferedReader(InputStreamReader(urlConnection.getInputStream()))
                onResponse(getLines(reader))
            } catch (e: Exception) {
                onErrorRequest(e.message ?: "Empty error")
            } finally {
                urlConnection?.disconnect()
            }

        } catch (e: MalformedURLException) {
            onMalformedURL()
        }
    }

    private fun onMalformedURL() {
        putLoadResult(FILMS_URL_MALFORMED_EXTRA)
        sendBroadcastLocal()
    }

    private fun onErrorRequest(errorMessage: String) {
        putLoadResult(FILMS_RESPONSE_ERROR_EXTRA)
        broadcastIntent.putExtra(FILMS_REQUEST_ERROR_MESSAGE_EXTRA, errorMessage)
        sendBroadcastLocal()
    }

    private fun onResponse(filmsJsonSTR: String?) {
        if (filmsJsonSTR == null) {
            onEmptyResponse()
        } else {
            onSuccessResponse(filmsJsonSTR)
        }
    }

    private fun onSuccessResponse(filmsJsonSTR: String) {
        putLoadResult(FILMS_RESPONSE_SUCCESS_EXTRA)
        broadcastIntent.putExtra(FILMS_JSON_EXTRA, filmsJsonSTR)
        sendBroadcastLocal()
    }

    private fun onEmptyResponse() {
        putLoadResult(FILMS_RESPONSE_EMPTY_EXTRA)
        sendBroadcastLocal()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))

    }

    private fun onEmptyIntent() {
        putLoadResult(FILMS_RESPONSE_EMPTY_EXTRA)
    }

    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(FILMS_LOAD_RESULT_DATA, result)

    }

    private fun sendBroadcastLocal() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }
}