package com.afrosin.filmsearch.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentMainBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.view.details.DetailsFragment
import com.afrosin.filmsearch.viewmodel.AppState
import com.afrosin.filmsearch.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeFilmDataSet() }
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        getFilmDataSet()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val filmsData = appState.filmsData
                binding.mainFragmentLoadingLayout.hide()
                adapter.setData(filmsData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.show()
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.hide()
                binding.mainFragmentRootView.showSnackBar(getString(R.string.error_text),
                    getString(R.string.reload_text), { getFilmDataSet() })
            }
        }
    }

    private fun getFilmDataSet() {
        if (isDataSetRus) {
            viewModel.getFilmsRus()
        } else {
            viewModel.getFilmsWorld()
        }
    }

    private fun changeLang() {
        isDataSetRus = !isDataSetRus
    }

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

private fun View.showSnackBar(
    text: String,
    actionText: String?,
    action: ((View) -> Unit)?,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).apply {
        if (actionText != null && action != null) {
            setAction(actionText, action)
        }
    }
        .show()
}

private fun View.showSnackBar(
    textId: Int,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    this.showSnackBar(
        context.resources.getString(textId),
        null,
        null,
        length
    )
}

private fun View.showSnackBar(
    textId: Int,
    actionTextId: Int,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    this.showSnackBar(
        context.resources.getString(textId),
        context.resources.getString(actionTextId),
        action,
        length
    )
}

private fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

private fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}