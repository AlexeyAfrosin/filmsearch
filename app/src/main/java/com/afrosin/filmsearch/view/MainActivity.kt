package com.afrosin.filmsearch.view

import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.ActivityMainBinding
import com.afrosin.filmsearch.view.filmHistory.FragmentFilmHistory
import com.afrosin.filmsearch.view.main.MainFragment
import com.afrosin.filmsearch.view.popularPerson.PopularPersonFragment

const val ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"

class MainActivity : AppCompatActivity() {
    private val receiver = MainBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)

        registerReceiver(receiver, IntentFilter(ACTION_CONNECTIVITY_CHANGE))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.film_details_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
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
                supportFragmentManager.apply {
                    beginTransaction()
                        .replace(R.id.container, SettingsFragment.newInstance())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
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