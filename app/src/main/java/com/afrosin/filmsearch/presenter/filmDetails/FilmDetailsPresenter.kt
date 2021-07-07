package com.afrosin.filmsearch.presenter.filmDetails

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.view.details.FilmDetailsView
import moxy.MvpPresenter

class FilmDetailsPresenter(
    private val film: Film
) : MvpPresenter<FilmDetailsView>() {


    override fun attachView(view: FilmDetailsView?) {
        super.attachView(view)
        viewState.init(film)
    }

}
