package com.afrosin.filmsearch.di.module.popularPerson

import com.afrosin.filmsearch.view.popularPerson.PopularPersonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class PopularPersonDiModule {
    @ContributesAndroidInjector
    abstract fun bindPopularPersonFragment(): PopularPersonFragment
}
