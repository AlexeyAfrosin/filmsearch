package com.afrosin.filmsearch.app

import android.util.Log
import com.afrosin.filmsearch.di.module.DaggerFilmSearchComponent
import com.afrosin.filmsearch.scheduler.SchedulerFactory
import com.afrosin.filmsearch.view.FilmScreens
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { error ->
            Log.e("GLOBAL_ERRORS", error.message.toString())
        }
    }

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerFilmSearchComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withSchedulers(SchedulerFactory.create())
            .withScreens(FilmScreens())
            .build()
}