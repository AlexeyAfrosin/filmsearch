package com.afrosin.filmsearch.viewmodel

import com.afrosin.filmsearch.model.Film

sealed class AppState {
    data class Success(val filmsData: List<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}