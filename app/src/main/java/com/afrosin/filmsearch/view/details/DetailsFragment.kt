package com.afrosin.filmsearch.view.details

//import com.afrosin.filmsearch.repository.POSTER_URL
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentDetailsBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.viewmodel.DetailsViewModel
import java.util.*

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val FILM_DATA = "film_data"
        fun newInstance(film: Film): DetailsFragment {
            val fragment = DetailsFragment()
//            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.detailsLiveData.observe(viewLifecycleOwner, { loadFilmNote(it) })
        return binding.root
    }

    private fun loadFilmNote(filmHistory: FilmHistory) {
        if (filmHistory != null) {
//           bunding.filmNote = filmHistory.note
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.getParcelable<Film>(FILM_DATA)?.let { film -> setData(film) }
    }

    private fun setData(film: Film) {
        with(film) {
            binding.filmItems.filmName.text = title
            binding.filmItems.filmDescription.text = overview
            binding.filmItems.saveNote.setOnClickListener {
                saveFilmNote(
                    id,
                    String.format("Заметка: %s", binding.filmItems.filmNote.text.toString()),
                    title,
                    true
                )
            }

//            if (posterPath != "") {
//                Glide.with(requireContext()).load(preparePosterUrl(posterPath))
//                    .into(binding.filmItems.filmPoster)
//            }
            saveFilmNote(id, "Просмотр информации о фильме", title, false)
        }
    }

    private fun saveFilmNote(filmId: Long, note: String?, title: String, showToast: Boolean) {
        if (note != null) {
            viewModel.saveFilmHistoryToDB(FilmHistory(0, filmId, note, Date(), title))
            if (showToast) {
                Toast.makeText(context, R.string.film_note_saved, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun preparePosterUrl(imageUrl: String?): String {
//        return "${POSTER_URL}${imageUrl}"
        return "${imageUrl}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}