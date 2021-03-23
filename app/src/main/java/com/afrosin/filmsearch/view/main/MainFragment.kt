package com.afrosin.filmsearch.view.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.BuildConfig
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentMainBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.utils.hide
import com.afrosin.filmsearch.utils.show
import com.afrosin.filmsearch.utils.showSnackBar
import com.afrosin.filmsearch.view.USE_ADULT_CONTENT_TAG
import com.afrosin.filmsearch.view.details.DetailsFragment
import com.afrosin.filmsearch.viewmodel.AppState
import com.afrosin.filmsearch.viewmodel.MainViewModel

private const val IS_DATASET_RUS_TAG = "IS_DATASET_RUS"

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(film: Film) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.FILM_DATA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })
    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeFilmDataSet() }
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        getIsDataSetParam()
        getFilmDataSet()
    }

    private fun getIsDataSetParam() {
        isDataSetRus =
            activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean(IS_DATASET_RUS_TAG, true)
                ?: true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val filmsData = appState.filmsData
                binding.includedLoadingLayout.loadingLayout.hide()
                adapter.setData(filmsData)
            }
            is AppState.Loading -> {
                binding.includedLoadingLayout.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.includedLoadingLayout.loadingLayout.hide()
                binding.mainFragmentRootView.showSnackBar(getString(R.string.error_text),
                    getString(R.string.reload_text), { getFilmDataSet() })
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getFilmDataSet() {
        val lang: String = when (isDataSetRus) {
            true -> "ru-RU"
            else -> "en-EN"
        }
        val includeAdult: Boolean =
            activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean(USE_ADULT_CONTENT_TAG, true)
                ?: true

        viewModel.getDataFromFromServer(BuildConfig.FILM_API_KEY, lang, includeAdult)
    }

    private fun changeLang() {
        isDataSetRus = !isDataSetRus
        saveDataSetLang()
    }

    private fun saveDataSetLang() {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(IS_DATASET_RUS_TAG, isDataSetRus)
                apply()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun changeFilmDataSet() = changeLang().also { getFilmDataSet() }

    companion object {
        fun newInstance() = MainFragment()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }
}