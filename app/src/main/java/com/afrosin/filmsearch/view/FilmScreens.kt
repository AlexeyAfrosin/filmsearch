package com.afrosin.filmsearch.view

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.view.details.DetailsFragment
import com.afrosin.filmsearch.view.main.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FilmScreens : IScreens {
    override fun films() = FragmentScreen {
        MainFragment.newInstance()
    }

    override fun filmDetails(film: Film) = FragmentScreen {
        DetailsFragment.newInstance(film)
    }

}