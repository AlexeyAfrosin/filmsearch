package com.afrosin.filmsearch.viewmodel

//import com.afrosin.filmsearch.app.App.Companion.getFilmViewHistoryDao
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.model.FilmHistory

class FilmHistoryViewModel(
    val filmHistoryLiveData: MutableLiveData<List<FilmHistory>> = MutableLiveData(),
//    private val historyRepository: LocalRepository = LocalRepositoryImp(getFilmViewHistoryDao())
) : ViewModel() {

    fun getAllFilmHistory() {
//        filmHistoryLiveData.value = historyRepository.getAllHistory()
    }
}