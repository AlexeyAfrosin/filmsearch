package com.afrosin.filmsearch.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrosin.filmsearch.databinding.FragmentMainRecyclerItemBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.utils.click

class FilmViewHolder(private val viewBinding: FragmentMainRecyclerItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    fun bind(film: Film, delegate: MainFragmentAdapter.Delegate?) {
        with(viewBinding) {

            mainFragmentRecyclerItemTextView.text = film.title

            root.click { delegate?.onFilmClicked(film) }
        }
    }
}