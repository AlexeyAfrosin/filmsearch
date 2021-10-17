package com.afrosin.filmsearch.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrosin.filmsearch.databinding.FragmentMainRecyclerItemBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.presenter.films.FilmDiffing

class MainFragmentAdapter(private val delegate: Delegate?) :
    ListAdapter<Film, FilmViewHolder>(FilmDiffing) {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * фильма из списка.
         * @param film фильм
         */
        fun onFilmClicked(film: Film)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmViewHolder = FilmViewHolder(
        FragmentMainRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}