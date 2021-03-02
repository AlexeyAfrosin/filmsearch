package com.afrosin.filmsearch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tbResult = findViewById<Button>(R.id.bt_result)

        FilmLibrary.films.add(Film("Фильм 1", 300))
        FilmLibrary.films.add(Film("Фильм 2", 600))
        FilmLibrary.films.add(Film("Фильм 3", 600))
        FilmLibrary.films.add(Film("Фильм 4", 600))
        FilmLibrary.films.add(Film("Фильм 5", 600))
        FilmLibrary.films.add(Film("Фильм 6", 600))
        FilmLibrary.films.add(Film("Фильм 7", 600))

        Log.d(TAG, "===================Цикл for each==================")
        for (film in FilmLibrary.films) {
            Log.d(TAG, film.name)
        }

        Log.d(TAG, "===================Цикл for==================")
        for (i in 0..FilmLibrary.films.size - 1) {
            Log.d(TAG, FilmLibrary.films[i].name)
        }

        Log.d(TAG, "===================Цикл for downTo==================")
        for (i in FilmLibrary.films.size - 1 downTo 1) {
            Log.d(TAG, FilmLibrary.films[i].name)
        }

        Log.d(TAG, "===================Цикл for until==================")
        for (i in 0 until FilmLibrary.films.size) {
            Log.d(TAG, FilmLibrary.films[i].name)
        }

        tbResult.setOnClickListener {
            val film = FilmLibrary.randomFilm().copy()
            Toast.makeText(this, "Молодец, твой фильм '${film.name}'", Toast.LENGTH_SHORT).show()
        }
    }
}