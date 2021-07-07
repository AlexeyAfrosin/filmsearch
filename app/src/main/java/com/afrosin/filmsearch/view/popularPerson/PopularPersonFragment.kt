package com.afrosin.filmsearch.view.popularPerson

import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.data.film.FilmRepository
import com.afrosin.filmsearch.databinding.FragmentPopularPersonBinding
import com.afrosin.filmsearch.model.Person
import com.afrosin.filmsearch.network.NetworkStateRepository
import com.afrosin.filmsearch.presenter.abstr.AbstractFragment
import com.afrosin.filmsearch.presenter.persons.PersonsPresenter
import com.afrosin.filmsearch.scheduler.Schedulers
import com.afrosin.filmsearch.view.FilmScreens
import com.afrosin.filmsearch.view.popularPerson.adapter.PopularPersonFragmentAdapter
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PopularPersonFragment : AbstractFragment(R.layout.fragment_popular_person),
    PopularPersonView {

    private val binding: FragmentPopularPersonBinding by viewBinding()
    private var adapter = PopularPersonFragmentAdapter()

    override fun init() {
        adapter = PopularPersonFragmentAdapter()

        with(binding) {
            popularPersonRecyclerView.layoutManager = LinearLayoutManager(context)
            popularPersonRecyclerView.adapter = adapter
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updateInsertedItem(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun showPersons(persons: List<Person>) = adapter.submitList(persons)

    @Inject
    lateinit var filmRepository: FilmRepository

    @Inject
    lateinit var schedulers: Schedulers

    @Inject
    lateinit var networkStateRepository: NetworkStateRepository

    @Inject
    lateinit var router: Router

    private val presenter: PersonsPresenter by moxyPresenter {
        PersonsPresenter(
            filmRepository,
            router,
            FilmScreens(),
            schedulers,
            networkStateRepository
        )
    }

    companion object {
        fun newInstance() = PopularPersonFragment()
    }

}