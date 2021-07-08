package com.afrosin.filmsearch.presenter.persons

import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.network.NetworkState
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.IScreens
import com.afrosin.filmsearch.view.popularPerson.PopularPersonView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter

class PersonsPresenter(
    private val filmRepository: FilmRepository,
    private val router: Router,
    private val screens: IScreens,
    private val schedulers: Schedulers,
    private val networkStateRepository: NetworkStateRepository
) : MvpPresenter<PopularPersonView>() {


    private val disposables = CompositeDisposable()

    override fun attachView(view: PopularPersonView?) {
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
                .doOnNext { displayPersons() }
                .subscribeOn(schedulers.backgroundNewThread())
                .subscribe()
        displayPersons()
    }

    private fun displayPersons() {
        disposables += filmRepository
            .fetchPopularPersons()
            .map { it.map(PersonMapper::map) }
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.backgroundNewThread())
            .subscribe(viewState::showPersons,
                { error -> error.message }
            )
    }

//    fun showPersonDetails(person: Person) {
//        router.navigateTo(screens.personDetails(person))
//    }


}
