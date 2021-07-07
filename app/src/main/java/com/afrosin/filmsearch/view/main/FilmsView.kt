package com.afrosin.filmsearch.view.main

import com.afrosin.filmsearch.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface FilmsView : MvpView {
    fun init()
    fun updateList()
    fun updateInsertedItem(position: Int)

    /**
     * Показывает список пользователей.
     * @param films список пользователей
     */
    @SingleState
    fun showFilms(films: List<Film>)
}