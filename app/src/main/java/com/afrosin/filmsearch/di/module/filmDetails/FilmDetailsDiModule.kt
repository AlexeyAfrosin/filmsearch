package com.afrosin.filmsearch.di.module.filmDetails

import com.afrosin.filmsearch.view.details.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FilmDetailsDiModule {
    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}
