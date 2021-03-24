package com.afrosin.filmsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String = "Фильм тест",
    val poster_path: String? = "posterPath",
    val overview: String? = "Описание фильма",
) : Parcelable

fun getFilmsWorld() = listOf(
    Film("Фильм Eng 1", "posterPath", "Описание фильма Eng 1"),
    Film("Фильм US 2", "posterPath", "Описание фильма US 2")
)

fun getFilmsRus() = listOf(
    Film("Фильм Ru 1", "posterPath", "Описание фильма Ru 1"),
    Film("Фильм Ru 2", "posterPath", "Описание фильма Ru 2")
)