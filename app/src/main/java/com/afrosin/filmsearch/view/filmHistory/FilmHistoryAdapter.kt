package com.afrosin.filmsearch.view.filmHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.utils.dateToStr
import kotlinx.android.synthetic.main.film_history_item.view.*

class FilmHistoryAdapter : RecyclerView.Adapter<FilmHistoryAdapter.RecyclerItemViewHolder>() {

    private var filmHistoryData: List<FilmHistory> = listOf()

    fun setData(data: List<FilmHistory>) {
        this.filmHistoryData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.film_history_item, parent, false) as View
        )
    }

    class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(filmHistory: FilmHistory) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.film_history_title.text = filmHistory.filmTitle
                itemView.film_note_date.text = dateToStr(filmHistory.dateCreated)
                itemView.film_note.text = filmHistory.note
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerItemViewHolder,
        position: Int
    ) {
        holder.bind(filmHistoryData[position])
    }

    override fun getItemCount(): Int {
        return filmHistoryData.size
    }
}