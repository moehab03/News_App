package com.route.news.data.repos.news_repo

import com.route.news.api.model.Article
import com.route.news.api.model.Source
import com.route.news.data.repos.news_repo.data_sources.local.LocalDataSource
import com.route.news.data.repos.news_repo.data_sources.local.LocalDataSourceImpl
import com.route.news.data.repos.news_repo.data_sources.remote.RemoteDataSource
import com.route.news.data.repos.news_repo.data_sources.remote.RemoteDataSourceImpl

class NewsRepoImpl : NewsRepo {
    private val remoteDataSource: RemoteDataSource = RemoteDataSourceImpl()
    private val localDataSource: LocalDataSource = LocalDataSourceImpl()
    override suspend fun getResourcesFromAPI(category: String): List<Source?>? {
        return if (isConnectToInternet()) {
            val sources = remoteDataSource.getResourcesFromAPI(category)
            localDataSource.saveSources(sources)
            sources
        } else {
            localDataSource.getResourcesFromRoom(category)
        }
    }

    override suspend fun getArticlesFromAPI(sourceID: String): List<Article?>? {
        return if (isConnectToInternet()) {
            val articles = remoteDataSource.getArticlesFromAPI(sourceID)
            localDataSource.saveArticles(articles)
            articles
        } else {
            localDataSource.getArticlesFromRoom(sourceID)
        }
    }

    private fun isConnectToInternet(): Boolean {
        return true
    }

}