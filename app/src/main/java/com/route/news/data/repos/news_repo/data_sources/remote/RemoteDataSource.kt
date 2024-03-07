package com.route.news.data.repos.news_repo.data_sources.remote

import com.route.news.api.model.Article
import com.route.news.api.model.Source

interface RemoteDataSource {
    suspend fun getResourcesFromAPI(category: String): List<Source?>?

    suspend fun getArticlesFromAPI(sourceID: String): List<Article?>?
}