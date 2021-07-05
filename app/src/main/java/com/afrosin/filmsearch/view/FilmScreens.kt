package com.afrosin.filmsearch.view

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.view.details.DetailsFragment
import com.afrosin.filmsearch.view.main.MainFragment
import com.afrosin.filmsearch.view.popularPerson.PopularPersonFragment
import com.afrosin.filmsearch.view.setting.SettingsFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class FilmScreens : IScreens {
    override fun films(): Screen = FragmentScreen {
        MainFragment.newInstance()
    }

    override fun filmSettings(): Screen = FragmentScreen {
        SettingsFragment.newInstance()
    }

    override fun filmDetails(film: Film): Screen = FragmentScreen {
        DetailsFragment.newInstance(film)
    }

    override fun persons(): Screen = FragmentScreen {
        PopularPersonFragment.newInstance()
    }

    override fun personDetails(person: Person): Screen = FragmentScreen {
        MainFragment.newInstance()
    }

}