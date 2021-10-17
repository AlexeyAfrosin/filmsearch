package com.afrosin.filmsearch.view.popularPerson.adapter

import androidx.recyclerview.widget.RecyclerView
import com.afrosin.filmsearch.databinding.FragmentPersonItemBinding
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.utils.setImageFromUrl

class PopularPersonViewHolder(private val viewBinding: FragmentPersonItemBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(person: Person) {
        with(viewBinding) {
            personNameTextView.text = person.name
            if (person.profilePath != "" && person.profilePath != null) {
                personProfile.setImageFromUrl(person.fullProfilePath())
            }

        }
    }
}