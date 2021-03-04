package com.afrosin.filmsearch.model

import java.util.*

data class Film(val name: String = "Фильм тест", val durationSeconds: Int = 3600){

    fun formatedDuration() : String{
        val df = java.text.SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return df.format(durationSeconds * 1000)
    }
}