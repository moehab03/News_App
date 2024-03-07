package com.route.news.ui.fragments.news

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.news.api.model.Article
import com.route.news.api.model.Source
import com.route.news.data.repos.news_repo.NewsRepo
import com.route.news.data.repos.news_repo.NewsRepoImpl
import kotlinx.coroutines.launch

class NewsFragmentViewModel : ViewModel() {
    private val newsRepo: NewsRepo = NewsRepoImpl()
    val sourcesListLiveData: MutableLiveData<List<Source?>?> = MutableLiveData(listOf())
    val articleListLiveData: MutableLiveData<List<Article?>> = MutableLiveData(listOf())
    val progressBarState = ObservableBoolean(false)
    val errorState = MutableLiveData("")

    fun getResourcesFromAPI(category: String) {
        progressBarState.set(true)
        viewModelScope.launch {
            try {
                sourcesListLiveData.value = newsRepo.getResourcesFromAPI(category)
                progressBarState.set(false)

            } catch (e: Exception) {
                progressBarState.set(false)
                errorState.value = e.localizedMessage ?: "Something Error"
            }
        }
    }

    fun getArticlesFromAPI(sourceID: String) {
        progressBarState.set(true)
        viewModelScope.launch {
            try {
                articleListLiveData.value = newsRepo.getArticlesFromAPI(sourceID)
                progressBarState.set(false)
            } catch (e: Exception) {
                progressBarState.set(false)
                errorState.value = e.localizedMessage ?: "Something Error"
            }
        }
    }

}