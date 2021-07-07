package com.afrosin.filmsearch.view.popularPerson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afrosin.filmsearch.databinding.FragmentPersonItemBinding
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.presenter.persons.PersonDiffing

class PopularPersonFragmentAdapter :
    ListAdapter<Person, PopularPersonViewHolder>(PersonDiffing) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularPersonViewHolder = PopularPersonViewHolder(
        FragmentPersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PopularPersonViewHolder, position: Int) =
        holder.bind(getItem(position))

}