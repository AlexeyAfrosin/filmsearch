package com.afrosin.filmsearch.view.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentMainBinding
import com.afrosin.filmsearch.model.Film
import com.afrosin.filmsearch.view.details.DetailsFragment
import com.afrosin.filmsearch.viewmodel.AppState
import com.afrosin.filmsearch.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

const val FILMS_INTENT_FILTER = "FILMS INTENT FILTER"
const val FILMS_RESPONSE_EMPTY_EXTRA = "FILMS RESPONSE EMPTY EXTRA"
const val FILMS_LOAD_RESULT_DATA = "FILMS LOAD RESULT DATA"
const val FILMS_RESPONSE_SUCCESS_EXTRA = "FILMS RESPONSE SUCCESS EXTRA"
const val FILMS_RESPONSE_ERROR_EXTRA = "FILMS RESPONSE ERROR EXTRA"
const val FILMS_JSON_EXTRA = "FILMS JSON EXTRA"
const val FILMS_REQUEST_ERROR_MESSAGE_EXTRA = "FILMS REQUEST ERROR MESSAGE EXTRA"
const val FILMS_URL_MALFORMED_EXTRA = "FILMS URL MALFORMED EXTRA"

private const val PROCESS_ERROR = "Обработка ошибки"


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
                    .replace(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.FILM_DATA, film)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    private val loadResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.getStringExtra(FILMS_LOAD_RESULT_DATA)) {
                FILMS_RESPONSE_SUCCESS_EXTRA -> {
                    val filmsJSON = intent.getStringExtra(FILMS_JSON_EXTRA)
                    val filmsType = object : TypeToken<List<Film>>() {}.type
                    val filmsStr = Gson().fromJson(filmsJSON, JsonObject::class.java)
                    val films: List<Film> = Gson().fromJson(filmsStr.get("results"), filmsType)
                    renderData(AppState.Success(films))
                }
                else -> binding.mainFragmentRootView.showSnackBar(getString(R.string.error_text),
                    getString(R.string.reload_text), { getFilmDataSet() })
            }
        }
    }

    private var isDataSetRus: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(
                loadResultReceiver, IntentFilter(
                    FILMS_INTENT_FILTER
                )
            )
        }
    }

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
        getFilmDataSet()
    }

    @RequiresApi(Build.VERSION_CODES.N)
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getFilmDataSet() {
        context?.let {
            it.startService(Intent(it, FilmsIntentService::class.java).apply {
                putExtra(IS_RUSSIAN_LANG_EXTRA, isDataSetRus)
            })
        }
    }

    private fun changeLang() {
        isDataSetRus = !isDataSetRus
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
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loadResultReceiver)
        }
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