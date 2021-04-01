package com.afrosin.filmsearch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrosin.filmsearch.model.PersonDetailsDTO
import com.afrosin.filmsearch.repository.RepositoryImpl
import com.afrosin.filmsearch.utils.converPersonDetailsDtoToPersonDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "Ошибка сервера"
private const val CORRUPTED_DATA = "Неполные данные"

class PersonDetailsViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getDataFromFromServer(person_id: Long, apiKey: String, language: String) {
        liveDataToObserve.value = AppState.Loading
        repositoryImpl.getPersonDetailsFromServer(person_id, apiKey, language, callBack)
    }

    private val callBack = object : Callback<PersonDetailsDTO> {

        override fun onResponse(
            call: Call<PersonDetailsDTO>,
            response: Response<PersonDetailsDTO>
        ) {
            val serverResponse: PersonDetailsDTO? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    onResponse(serverResponse)
                } else {
                    onErrorRequest(SERVER_ERROR)
                }
            )
        }

        override fun onFailure(call: Call<PersonDetailsDTO>, t: Throwable) {
            onErrorRequest(t.message.toString())
        }

        private fun onResponse(personDetailsDTO: PersonDetailsDTO?): AppState {
            return if (personDetailsDTO == null) {
                onEmptyResponse()
            } else {
                onSuccessResponse(personDetailsDTO)
            }
        }

        private fun onSuccessResponse(personDetailsDTO: PersonDetailsDTO): AppState {
            return AppState.SuccessPersonDetails(
                converPersonDetailsDtoToPersonDetails(
                    personDetailsDTO
                )
            )
        }

        private fun onEmptyResponse(): AppState {
            return onErrorRequest(CORRUPTED_DATA)
        }

        private fun onErrorRequest(errorName: String): AppState {
            return AppState.Error(Throwable(errorName))
        }

    }
}



