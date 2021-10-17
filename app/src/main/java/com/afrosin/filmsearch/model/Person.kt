package com.afrosin.filmsearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afrosin.filmsearch.di.module.network.IMAGE_URL
import com.google.gson.annotations.SerializedName

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey
    @SerializedName("id") val id: Long,
    @SerializedName("profile_path") val profilePath: String? = "",
    @SerializedName("name") val name: String
) {
    fun fullProfilePath(): String = "${IMAGE_URL}${profilePath}"
}
