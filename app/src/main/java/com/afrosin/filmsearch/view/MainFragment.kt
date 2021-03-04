package com.afrosin.filmsearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.databinding.FragmentMainBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.viewmodel.AppState
import com.afrosin.filmsearch.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getFilms()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val filmsData = appState.filmsData
                binding.loadingLayout.visibility = View.GONE
                setData(filmsData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainFrameLayout, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getFilms() }
                    .show()
            }
        }
    }

    private fun setData(filmsData: Film) {
        binding.filmItems.filmName.text = filmsData.name
        binding.filmItems.filmDuration.text = filmsData.formatedDuration()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}