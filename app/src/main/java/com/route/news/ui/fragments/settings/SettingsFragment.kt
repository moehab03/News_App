package com.route.news.ui.fragments.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.route.news.R
import com.route.news.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLanguageSpinner()
    }

    private fun initLanguageSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.languages, R.layout.spinner_layout
        )
        arrayAdapter.setDropDownViewResource(R.layout.spinner_layout)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            // add required code
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Do Nothing
    }

}