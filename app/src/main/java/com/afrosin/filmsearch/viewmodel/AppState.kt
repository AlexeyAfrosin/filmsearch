package com.afrosin.filmsearch.viewmodel

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.model.PersonDetails

sealed class AppState {
    data class Success(val filmsData: List<Film>) : AppState()
    data class SuccessPopularPerson(val popularPersonData: List<Person>) : AppState()
    data class SuccessPersonDetails(val personDetails: PersonDetails) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()

    companion object {
        const val SERVER_ERROR = "Ошибка сервера"
        const val CORRUPTED_DATA = "Неполные данные"
    }
}