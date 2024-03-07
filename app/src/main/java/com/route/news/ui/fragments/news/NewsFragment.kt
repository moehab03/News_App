package com.route.news.ui.fragments.news

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.route.news.Constant
import com.route.news.adapter.ArticlesAdapter
import com.route.news.api.model.Article
import com.route.news.api.model.Source
import com.route.news.databinding.FragmentNewsBinding
import com.route.news.ui.activities.ViewArticleActivity

class NewsFragment(
    private val category: String,
    private val onResume: () -> Unit
) : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsFragmentViewModel

    private val adapter = ArticlesAdapter(listOf()) { article ->
        val intent = Intent(activity, ViewArticleActivity::class.java)
        intent.putExtra(Constant.ARTICLE, article)
        startActivity(intent)
    }
    private var resourcesState = false
    var source = Source()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getResourcesFromAPI(category)
        onTabSelectedListener()
        observeToLiveData()
        binding.vm = viewModel
    }

    override fun onResume() {
        super.onResume()
        onResume.invoke()
    }

    private fun observeToLiveData() {
        viewModel.sourcesListLiveData.observe(viewLifecycleOwner) {
            showTabs(it)
        }
        viewModel.articleListLiveData.observe(viewLifecycleOwner) {
            initArticlesRecyclerView(it)
        }
        viewModel.errorState.observe(viewLifecycleOwner) {
            if (it != "")
                showErrorView(true, it)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[NewsFragmentViewModel::class.java]
    }

    private fun initArticlesRecyclerView(articles: List<Article?>) {
        adapter.updateArticles(articles)
        binding.articleRecyclerView.adapter = adapter
    }

    private fun showTabs(sources: List<Source?>?) {
        sources?.forEach { source ->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.tabLayout.addTab(tab)
        }
        binding.tabLayout.apply {
            selectTab(getTabAt(0))
        }
        tabMargin()
    }

    private fun tabMargin() {
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        tabs.forEach {
            val layoutParams = it.layoutParams as LinearLayout.LayoutParams
            layoutParams.marginStart = 20
            it.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    private fun onTabSelectedListener() {
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                adapter.updateArticles(listOf())
                val source = tab?.tag as Source
                viewModel.getArticlesFromAPI(source.id.toString())
                showErrorView(false)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                adapter.updateArticles(listOf())
                source = tab?.tag as Source
                viewModel.getArticlesFromAPI(source.id.toString())
                showErrorView(false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun showErrorView(state: Boolean, errorMsg: String = "") {
        binding.errorLayout.apply {
            root.isVisible = state
            errorTV.text = errorMsg
            retryBtn.setOnClickListener {
                if (!resourcesState)
                    viewModel.getResourcesFromAPI(category)
                else {
                    source.id?.let {
                        viewModel.getArticlesFromAPI(it)
                    }
                }
                root.isVisible = false
            }
        }
    }
}