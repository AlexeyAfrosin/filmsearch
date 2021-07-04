package com.afrosin.filmsearch.presenter.films

import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.network.NetworkState
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.IScreens
import com.afrosin.filmsearch.view.main.FilmsView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class FilmsPresenter(
    private val filmRepository: FilmRepository,
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val networkStateRepository: NetworkStateRepository
) : MvpPresenter<FilmsView>() {


    private val disposables = CompositeDisposable()

    override fun attachView(view: FilmsView?) {
        super.attachView(view)
        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun loadData() {
        disposables +=
            networkStateRepository
                .watchForNetworkState()
                .filter { networkState -> networkState == NetworkState.CONNECTED }
                .observeOn(schedulers.main())
                .doOnNext { displayFilms() }
                .subscribeOn(schedulers.backgroundNewThread())
                .subscribe()
        displayFilms()
    }

    private fun displayFilms() {
        disposables += filmRepository
            .fetchFilms()
            .map { it.map(FilmMapper::map) }
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.backgroundNewThread())
            .subscribe(viewState::showFilms,
                { error -> error.message }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}
