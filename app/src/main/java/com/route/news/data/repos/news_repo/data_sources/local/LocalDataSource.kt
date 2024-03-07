package com.route.news.data.repos.news_repo.data_sources.local

import com.route.news.api.model.Article
import com.route.news.api.model.Source

interface LocalDataSource {
    suspend fun getResourcesFromRoom(category: String): List<Source?>?

    suspend fun getArticlesFromRoom(sourceID: String): List<Article?>?

    suspend fun saveSources(sources: List<Source?>?)
    suspend fun saveArticles(articles: List<Article?>?)
}