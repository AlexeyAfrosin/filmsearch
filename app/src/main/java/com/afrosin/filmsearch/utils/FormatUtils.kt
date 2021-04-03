package com.afrosin.filmsearch.utils

import java.text.SimpleDateFormat
import java.util.*

fun dateToStr(date: Date, dateFormat: String = "dd MMMM yyyy HH:mm:ss"): String {
    val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    return simpleDateFormat.format(date)
}