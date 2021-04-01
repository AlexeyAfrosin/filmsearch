package com.afrosin.filmsearch.view.popularPerson

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.repository.POSTER_URL
import com.bumptech.glide.Glide

class PopularPersonFragmentAdapter(private var onItemViewClickListener: PopularPersonFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<PopularPersonFragmentAdapter.PersonViewHolder>() {

    private var personData: List<Person> = listOf()
    private lateinit var context: Context

    fun setData(data: List<Person>, contextIn: Context) {
        personData = data
        context = contextIn
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_person_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personData[position])
    }

    override fun getItemCount(): Int {
        return personData.size
    }

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(person: Person) {
            itemView.apply {
                findViewById<TextView>(R.id.personNameTextView).text = person.name
                if (person.profilePath != "") {
                    Glide.with(context).load("$POSTER_URL${person.profilePath}")
                        .into(findViewById(R.id.personProfile))
                }
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(person)
                }
            }
        }
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

}