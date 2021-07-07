package com.afrosin.filmsearch.view.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.afrosin.filmsearch.R
import com.afrosin.filmsearch.databinding.FragmentSettingsBinding
import com.afrosin.filmsearch.presenter.SettingsPresenter
import com.afrosin.filmsearch.presenter.abstr.AbstractFragment
import moxy.ktx.moxyPresenter

const val USE_ADULT_CONTENT_TAG = "USE_ADULT_CONTENT_TAG"

class SettingsFragment : AbstractFragment(R.layout.fragment_settings), SettingsView {

    private val binding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scUseAdultContent.isChecked =
            activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean(USE_ADULT_CONTENT_TAG, true)
                ?: true

        binding.scUseAdultContent.setOnCheckedChangeListener { _, isChecked ->
            activity?.let {
                with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                    putBoolean(USE_ADULT_CONTENT_TAG, isChecked)
                    apply()
                }
            }
        }

    }

    private val presenter: SettingsPresenter by moxyPresenter {
        SettingsPresenter()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}