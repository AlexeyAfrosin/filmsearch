package com.afrosin.filmsearch.network

import android.content.Context
import io.reactivex.rxjava3.core.Observable

class NetworkStateRepositoryImp(private val context: Context) : NetworkStateRepository {

    override fun watchForNetworkState(): Observable<NetworkState> =
        NetworkStateObservable(context)
//            .cacheWithInitialCapacity(1)
//            .skip(1)
}
