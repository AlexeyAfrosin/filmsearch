package com.afrosin.filmsearch.presenter.persons

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.afrosin.filmsearch.model.Person

object PersonDiffing : DiffUtil.ItemCallback<Person>() {
    private val payload = Any()
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Person, newItem: Person) = payload
}
