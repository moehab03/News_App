package com.route.news.ui.fragments.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.route.news.databinding.FragmentCategoryBinding

class CategoryFragment(
    val onCategoryClick: (category: String) -> Unit,
    private val onResume: (category: String) -> Unit
) : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private var category = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initAppBar()
        initCategoryClickListener()

    }

    override fun onResume() {
        super.onResume()
        onResume.invoke(category)
    }

    private fun initCategoryClickListener() {
        binding.apply {

            sportsBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
            techBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
            healthBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
            businessBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
            entertainmentBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
            scienceBtn.setOnClickListener {
                category = it.tag as String
                onCategoryClick(category)
            }
        }
    }


}