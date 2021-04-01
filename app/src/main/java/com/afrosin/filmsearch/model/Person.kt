package com.afrosin.filmsearch.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val profilePath: String,
    val name: String,
    val id: Long
) : Parcelable
