package com.afrosin.filmsearch.di.module.network

import android.content.Context
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.network.NetworkStateRepositoryImp
import dagger.Module
import dagger.Provides

@Module
class NetworkStateModule {
    @Provides
    fun providerNetworkStateRepository(context: Context): NetworkStateRepository =
        NetworkStateRepositoryImp(context)

}
