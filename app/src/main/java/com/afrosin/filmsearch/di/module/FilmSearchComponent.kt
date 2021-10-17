package com.afrosin.filmsearch.di.module

import android.content.Context
import com.afrosin.filmsearch.app.App
import com.afrosin.filmsearch.di.module.film.FilmModule
import com.afrosin.filmsearch.di.module.filmDetails.FilmDetailsModule
import com.afrosin.filmsearch.di.module.network.NetworkStateModule
import com.afrosin.filmsearch.di.module.popularPerson.PopularPersonModule
import com.afrosin.filmsearch.di.module.settings.SettingsModule
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.FilmScreens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        MainModule::class,
        NetworkStateModule::class,
        FilmModule::class,
        SettingsModule::class,
        PopularPersonModule::class,
        FilmDetailsModule::class
    ]
)
interface FilmSearchComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(scheduler: Schedulers): Builder

        @BindsInstance
        fun withScreens(filmScreens: FilmScreens): Builder

        fun build(): FilmSearchComponent
    }
}
