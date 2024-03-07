package com.route.news.data.repos.news_repo.data_sources.remote

import com.route.news.Constant
import com.route.news.api.ApiManager
import com.route.news.api.model.Article
import com.route.news.api.model.Source

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getResourcesFromAPI(category: String): List<Source?>? {
        return ApiManager.getWebService().getResources(Constant.API_KEY, category).sources
    }

    override suspend fun getArticlesFromAPI(sourceID: String): List<Article?>? {
        return ApiManager.getWebService().getArticles(Constant.API_KEY, sourceID).articles
    }
}