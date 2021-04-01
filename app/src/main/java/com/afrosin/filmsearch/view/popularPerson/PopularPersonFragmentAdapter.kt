package com.afrosin.filmsearch.view.popularPerson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.model.Person

class PopularPersonFragmentAdapter(private var onItemViewClickListener: PopularPersonFragment.OnItemViewClickListener?) :
    RecyclerView.Adapter<PopularPersonFragmentAdapter.PersonViewHolder>() {

    private var personData: List<Person> = listOf()

    fun setData(data: List<Person>) {
        personData = data
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