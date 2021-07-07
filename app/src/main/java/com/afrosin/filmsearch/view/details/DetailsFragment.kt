package com.afrosin.filmsearch.view.details

import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentDetailsBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.presenter.abstr.AbstractFragment
import com.afrosin.filmsearch.presenter.filmDetails.FilmDetailsPresenter
import com.afrosin.filmsearch.utils.setImageFromUrl
import moxy.ktx.moxyPresenter

class DetailsFragment(film: Film) : AbstractFragment(R.layout.fragment_details), FilmDetailsView {

    private val binding: FragmentDetailsBinding by viewBinding()

    companion object {
        fun newInstance(film: Film): DetailsFragment = DetailsFragment(film)
    }

    private val presenter: FilmDetailsPresenter by moxyPresenter {
        FilmDetailsPresenter(
            film
        )
    }

    // TODO подумать куда деть ссылку
    private val posterUrl = "https://image.tmdb.org/t/p/w300"

    override fun init(film: Film) {
        with(film) {
            with(binding) {
                filmItems.filmName.text = title
                filmItems.filmDescription.text = overview
                if (film.posterPath != "" && film.posterPath != null) {
                    filmItems.filmPoster.setImageFromUrl("$posterUrl${film.posterPath}")
                }
            }
        }
    }
}