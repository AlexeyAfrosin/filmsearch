package com.afrosin.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve
    fun getFilmsRus() = getDataFromLocalSource(isRussian = true)
    fun getFilmsWorld() = getDataFromLocalSource(isRussian = false)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussian)
                        repositoryImpl.getFilmsLocalStorageRus()
                    else
                        repositoryImpl.getFilmsLocalStorageWorld()
                )
            )
        }.start()
    }
}