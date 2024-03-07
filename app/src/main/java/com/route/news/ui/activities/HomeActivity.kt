package com.route.news.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.route.news.R
import com.route.news.databinding.ActivityHomeBinding
import com.route.news.ui.fragments.category.CategoryFragment
import com.route.news.ui.fragments.news.NewsFragment
import com.route.news.ui.fragments.search.SearchFragment
import com.route.news.ui.fragments.settings.SettingsFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val categoryFragment = CategoryFragment({
        showFragment(NewsFragment(it) {
            initNewsAppBar(it)
        })
    }, { initCategoryAppBar() })

    private val searchFragment = SearchFragment {
        binding.appBar.root.isVisible = true
        supportFragmentManager.popBackStack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        showFragment(categoryFragment)
        initCategoryAppBar()
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.categoryMenu -> {
                    showFragment(categoryFragment)
                }

                R.id.settingsMenu -> {
                    showFragment(SettingsFragment())
                }
            }
            binding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun initCategoryAppBar() {
        binding.appBar.apply {
            root.isVisible = true
            appTitle.text = getText(R.string.news_app)
            appTitle.isVisible = true
            menuIcon.isVisible = true
            searchIcon.isVisible = false
            menuIcon.setOnClickListener {
                binding.drawerLayout.open()
            }
        }
    }

    private fun initNewsAppBar(category: String) {
        binding.appBar.apply {
            appTitle.text = getText(R.string.news_app)
            appTitle.isVisible = true
            appTitle.text = category
            menuIcon.isVisible = true
            searchIcon.isVisible = true
            menuIcon.setOnClickListener {
                binding.drawerLayout.open()
            }
            searchIcon.setOnClickListener {
                onSearchIconClickListener()
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack("")
            .commit()
    }

    private fun onSearchIconClickListener() {
        showFragment(searchFragment)
        binding.appBar.root.isVisible = false
    }
}