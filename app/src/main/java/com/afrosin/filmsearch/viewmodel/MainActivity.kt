package com.afrosin.filmsearch.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.ActivityMainBinding
import com.afrosin.filmsearch.view.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}