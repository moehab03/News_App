package com.route.news.data.repos.news_repo.data_sources.local

import com.route.news.api.model.Article
import com.route.news.api.model.Source

class LocalDataSourceImpl : LocalDataSource {
    override suspend fun getResourcesFromRoom(category: String): List<Source?> {
        return listOf()
    }

    override suspend fun getArticlesFromRoom(sourceID: String): List<Article?> {
        return listOf()
    }

    override suspend fun saveSources(sources: List<Source?>?) {

    }

    override suspend fun saveArticles(articles: List<Article?>?) {

    }
}