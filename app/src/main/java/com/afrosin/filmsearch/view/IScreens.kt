package com.afrosin.filmsearch.view

import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.Person
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun films(): Screen
    fun filmDetails(film: Film): Screen
    fun filmSettings(): Screen
    fun persons(): Screen
}