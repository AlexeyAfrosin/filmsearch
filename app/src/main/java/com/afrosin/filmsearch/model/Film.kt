package com.afrosin.filmsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Film(
    val name: String = "Фильм тест",
    val durationSeconds: Int = 3600,
    val description: String = "Описание фильма"
) : Parcelable {

    fun formattedDuration(): String {
        val df = java.text.SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return df.format(durationSeconds * 1000)
    }
}

fun getFilmsWorld(): List<Film> {
    return listOf(
        Film("Фильм Eng 1", 3600, "Описание фильма Eng 1"),
        Film("Фильм US 2", 4600, "Описание фильма US 2")
    )
}

fun getFilmsRus(): List<Film> {
    return listOf(
        Film("Фильм Ru 1", 3600, "Описание фильма Ru 1"),
        Film("Фильм Ru 2", 4600, "Описание фильма Ru 2")
    )
}