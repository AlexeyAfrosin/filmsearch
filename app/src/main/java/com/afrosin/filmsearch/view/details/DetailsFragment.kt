package com.afrosin.filmsearch.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afrosin.filmsearch.databinding.FragmentDetailsBinding
import com.afrosin.filmsearch.model.Film

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val FILM_DATA = "film_data"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.getParcelable<Film>(FILM_DATA)

        if (film != null) {
            setData(film)
        }
    }

    private fun setData(film: Film) {
        binding.filmItems.filmName.text = film.name
        binding.filmItems.filmDuration.text = film.formattedDuration()
        binding.filmItems.filmDescription.text = film.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}