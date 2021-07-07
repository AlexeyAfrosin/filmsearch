package com.afrosin.filmsearch.network

import io.reactivex.rxjava3.core.Observable

interface NetworkStateRepository {
    fun watchForNetworkState(): Observable<NetworkState>
}
