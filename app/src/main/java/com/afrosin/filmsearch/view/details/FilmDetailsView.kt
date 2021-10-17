package com.afrosin.filmsearch.view.details

import com.afrosin.filmsearch.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilmDetailsView : MvpView {
    fun init(film: Film)
}