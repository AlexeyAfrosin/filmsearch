package com.afrosin.filmsearch.viewmodel

//import com.afrosin.filmsearch.app.App.Companion.getFilmViewHistoryDao
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.model.FilmHistory

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<FilmHistory> = MutableLiveData(),
//    private val historyRepository: LocalRepository = LocalRepositoryImp(getFilmViewHistoryDao())
) : ViewModel() {

    fun saveFilmHistoryToDB(filmHistory: FilmHistory) {
//        historyRepository.saveFilmHistoryEntity(filmHistory)
    }
}