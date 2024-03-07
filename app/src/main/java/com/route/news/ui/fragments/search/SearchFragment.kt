package com.route.news.ui.fragments.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.route.news.Constant
import com.route.news.adapter.ArticlesAdapter
import com.route.news.api.model.Article
import com.route.news.databinding.FragmentSearchBinding
import com.route.news.ui.activities.ViewArticleActivity


class SearchFragment(private val cancelClick: (fragment: Fragment) -> Unit) : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: ArticlesAdapter
    private lateinit var viewModel: SearchFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateRecyclerView(listOf())
        initViewModel()
        observeToLiveData()
        newsSearch()
        onCancelSearchIconClick()
    }

    private fun newsSearch() {
        binding.searchBar.searchET.addTextChangedListener {
            viewModel.articleSearchInAPI(binding.searchBar.searchET.text.toString())
        }
    }

    private fun observeToLiveData() {
        viewModel.articleListLiveData.observe(viewLifecycleOwner) {
            updateRecyclerView(it)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[SearchFragmentViewModel::class.java]
    }

    private fun updateRecyclerView(articles: List<Article?>) {
        adapter = ArticlesAdapter(articles) {
            val intent = Intent(activity, ViewArticleActivity::class.java)
            intent.putExtra(Constant.ARTICLE, it)
            startActivity(intent)
        }
        binding.searchRecyclerView.adapter = adapter
    }

    private fun onCancelSearchIconClick() {
        binding.searchBar.cancelSearchBtn.setOnClickListener {
            cancelClick.invoke(this)
        }
    }
}
