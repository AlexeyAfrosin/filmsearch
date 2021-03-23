package com.afrosin.filmsearch.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afrosin.filmsearch.databinding.FragmentSettingsBinding

const val USE_ADULT_CONTENT_TAG = "USE_ADULT_CONTENT_TAG"

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scUseAdultContent.isChecked =
            activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean(USE_ADULT_CONTENT_TAG, true)
                ?: true

        binding.scUseAdultContent.setOnCheckedChangeListener { compoundButton, isChecked ->
            activity?.let {
                with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                    putBoolean(USE_ADULT_CONTENT_TAG, isChecked)
                    apply()
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}