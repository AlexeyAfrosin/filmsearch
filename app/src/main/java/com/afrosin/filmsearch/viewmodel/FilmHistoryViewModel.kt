package com.afrosin.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.app.App.Companion.getFilmViewHistoryDao
import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.repository.LocalRepository
import com.afrosin.filmsearch.repository.LocalRepositoryImp

class FilmHistoryViewModel(
    val filmHistoryLiveData: MutableLiveData<List<FilmHistory>> = MutableLiveData(),
    private val historyRepository: LocalRepository = LocalRepositoryImp(getFilmViewHistoryDao())
) : ViewModel() {

    fun getAllFilmHistory() {
        filmHistoryLiveData.value = historyRepository.getAllHistory()
    }
}