package com.afrosin.filmsearch.di.module.film

import com.afrosin.filmsearch.view.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FilmDiModule {
    @ContributesAndroidInjector
    abstract fun bindMainFragment(): MainFragment
}
