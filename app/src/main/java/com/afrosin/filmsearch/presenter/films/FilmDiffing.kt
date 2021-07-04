package com.afrosin.filmsearch.presenter.films

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.afrosin.filmsearch.model.Film

object FilmDiffing : DiffUtil.ItemCallback<Film>() {
    private val payload = Any()
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Film, newItem: Film) = payload
}
