package com.afrosin.filmsearch.presenter.films

import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.network.NetworkState
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers.trampoline
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class FilmsPresenterTest {

    @Mock
    private lateinit var presenter: FilmsPresenter

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var screens: IScreens

    class TrampolineSchedulerProvider : Schedulers {
        override fun backgroundIo() = trampoline()
        override fun backgroundNewThread() = trampoline()
        override fun backgroundComputation() = trampoline()
        override fun main() = trampoline()
    }

    private var schedulerTest = TrampolineSchedulerProvider()

    @Mock
    private lateinit var networkStateRepository: NetworkStateRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = FilmsPresenter(
            filmRepository,
            router,
            screens,
            schedulerTest,
            networkStateRepository
        )
    }


    @Test
    fun loadData_Test() {
        val lang = "ru-ru"
        val includeAdult = false
        RxJavaPlugins.setErrorHandler { }


        `when`(
            networkStateRepository.watchForNetworkState()
        ).thenReturn(just(NetworkState.CONNECTED))

        `when`(
            filmRepository.fetchFilms(lang, includeAdult)
        ).thenReturn(
            just(
                listOf(
                    Film(
                        1,
                        false,
                        "overview",
                        "posterPath",
                        "title"
                    )
                )
            )
        )

        presenter.loadData(lang)

        verify(filmRepository, times(2)).fetchFilms(lang, includeAdult)
    }

}