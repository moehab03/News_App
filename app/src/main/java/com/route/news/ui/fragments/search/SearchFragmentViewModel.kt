package com.route.news.ui.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.news.Constant
import com.route.news.api.ApiManager
import com.route.news.api.model.Article
import kotlinx.coroutines.launch

class SearchFragmentViewModel : ViewModel() {
    var articleListLiveData: MutableLiveData<List<Article?>> = MutableLiveData(listOf())
    fun articleSearchInAPI(text: String) {

        viewModelScope.launch {
            try {
                val searchResponse =
                    ApiManager.getWebService()
                        .getArticles(apiKey = Constant.API_KEY, searchText = text)
                articleListLiveData.value = searchResponse.articles!!
            } catch (_: Exception) {
            }
        }
    }
}