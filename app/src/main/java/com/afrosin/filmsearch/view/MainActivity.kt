package com.afrosin.filmsearch.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.presenter.abstr.AbstractActivity
import com.afrosin.filmsearch.view.filmHistory.FragmentFilmHistory
import com.afrosin.filmsearch.view.popularPerson.PopularPersonFragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AbstractActivity(R.layout.activity_main) {


    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var filmScreens: FilmScreens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.replaceScreen(filmScreens.films())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.film_details_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.film_history_menu -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, FragmentFilmHistory.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            R.id.film_settings -> {
                router.navigateTo(filmScreens.filmSettings())
                true
            }
            R.id.menu_show_popular_person -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, PopularPersonFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}