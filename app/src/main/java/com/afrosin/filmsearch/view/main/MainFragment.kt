package com.afrosin.filmsearch.view.main

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.databinding.FragmentMainBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.presenter.abstr.AbstractFragment
import com.afrosin.filmsearch.presenter.films.FilmsPresenter
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.FilmScreens
import com.afrosin.filmsearch.view.main.adapter.MainFragmentAdapter
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainFragment : AbstractFragment(R.layout.fragment_main), FilmsView,
    MainFragmentAdapter.Delegate {


    private val binding: FragmentMainBinding by viewBinding()
    private var adapter = MainFragmentAdapter(delegate = this)

    override fun init() {
        adapter = MainFragmentAdapter(this)

        with(binding) {
            mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
            mainFragmentRecyclerView.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun showFilms(films: List<Film>) = adapter.submitList(films)

    @Inject
    lateinit var filmRepository: FilmRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var networkStateRepository: NetworkStateRepository

    @Inject
    lateinit var router: Router

    private val presenter: FilmsPresenter by moxyPresenter {
        FilmsPresenter(
            filmRepository,
            router,
            FilmScreens(),
            schedulers,
            networkStateRepository
        )
    }

    override fun onFilmClicked(film: Film) {
        presenter.showFilmDetails(film)
    }


    companion object {
        fun newInstance() = MainFragment()
    }


}