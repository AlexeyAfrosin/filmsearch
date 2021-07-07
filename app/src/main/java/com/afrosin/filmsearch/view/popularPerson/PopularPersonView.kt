package com.afrosin.filmsearch.view.popularPerson

import com.afrosin.filmsearch.model.Person
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState

@StateStrategyType(AddToEndSingleStrategy::class)
interface PopularPersonView : MvpView {
    fun init()
    fun updateList()
    fun updateInsertedItem(position: Int)

    /**
     * Показывает список популярных актеров.
     * @param persons список популярных актеров
     */
    @SingleState
    fun showPersons(persons: List<Person>)
}