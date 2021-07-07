package com.afrosin.filmsearch.di.module.popularPerson


import com.afrosin.filmsearch.di.module.network.NetworkModule
import dagger.Module


@Module(includes = [PopularPersonDiModule::class, NetworkModule::class])
class PopularPersonModule {

}
