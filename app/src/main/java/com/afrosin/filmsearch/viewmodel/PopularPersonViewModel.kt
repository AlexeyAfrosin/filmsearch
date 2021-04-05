package com.afrosin.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.model.FilmsPersonPopularDTO
import com.afrosin.filmsearch.repository.RepositoryImpl
import com.afrosin.filmsearch.utils.converFilmsPersonPopularDtoToFilmsPersonPopularList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularPersonViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getDataFromFromServer(apiKey: String, language: String) {
        liveDataToObserve.value = AppState.Loading
        repositoryImpl.getPopularPersonFromServer(apiKey, language, callBack)
    }

    private val callBack = object : Callback<FilmsPersonPopularDTO> {

        override fun onResponse(
            call: Call<FilmsPersonPopularDTO>,
            response: Response<FilmsPersonPopularDTO>
        ) {
            val serverResponse: FilmsPersonPopularDTO? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    onResponse(serverResponse)
                } else {
                    onErrorRequest(AppState.SERVER_ERROR)
                }
            )
        }

        override fun onFailure(call: Call<FilmsPersonPopularDTO>, t: Throwable) {
            onErrorRequest(t.message.toString())
        }

        private fun onResponse(filmsPersonPopularDTO: FilmsPersonPopularDTO?): AppState {
            return if (filmsPersonPopularDTO == null) {
                onEmptyResponse()
            } else {
                onSuccessResponse(filmsPersonPopularDTO)
            }
        }

        private fun onSuccessResponse(filmsPersonPopularDTO: FilmsPersonPopularDTO): AppState {
            return AppState.SuccessPopularPerson(
                converFilmsPersonPopularDtoToFilmsPersonPopularList(
                    filmsPersonPopularDTO
                )
            )
        }

        private fun onEmptyResponse(): AppState {
            return onErrorRequest(AppState.CORRUPTED_DATA)
        }

        private fun onErrorRequest(errorName: String): AppState {
            return AppState.Error(Throwable(errorName))
        }

    }
}



