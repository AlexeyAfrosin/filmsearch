package com.afrosin.filmsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val title: String = "Фильм тест",
    val posterPath: String? = "",
    val overview: String? = "Описание фильма",
    val id: Long,
    val note: String? = null
) : Parcelable

fun getFilmsWorld() = listOf(
    Film("Фильм Eng 1", "posterPath", "Описание фильма Eng 1", 1),
    Film("Фильм US 2", "posterPath", "Описание фильма US 2", 2)
)

fun getFilmsRus() = listOf(
    Film("Фильм Ru 1", "posterPath", "Описание фильма Ru 1", 3),
    Film("Фильм Ru 2", "posterPath", "Описание фильма Ru 2", 4)
)