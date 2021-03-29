package com.afrosin.filmsearch.view.filmHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.databinding.FragmentFilmHistoryBinding
import com.afrosin.filmsearch.model.FilmHistory
import com.afrosin.filmsearch.utils.show
import com.afrosin.filmsearch.viewmodel.FilmHistoryViewModel

class FragmentFilmHistory : Fragment() {

    private var _binding: FragmentFilmHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmHistoryViewModel by lazy {
        ViewModelProvider(this).get(FilmHistoryViewModel::class.java)
    }

    private val adapter: FilmHistoryAdapter by lazy { FilmHistoryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyFragmentRecyclerview.adapter = adapter
        viewModel.filmHistoryLiveData.observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getAllFilmHistory()

    }

    private fun renderData(filmHistoryList: List<FilmHistory>?) {
        binding.historyFragmentRecyclerview.show()
        if (filmHistoryList != null) {
            adapter.setData(filmHistoryList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentFilmHistory()
    }
}