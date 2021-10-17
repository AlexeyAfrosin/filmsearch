package com.afrosin.filmsearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afrosin.filmsearch.di.module.network.IMAGE_URL
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class Film(
    @PrimaryKey
    @SerializedName("id") val id: Long,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String
) {
    fun fullPosterPath(): String = "${IMAGE_URL}${posterPath}"
}

