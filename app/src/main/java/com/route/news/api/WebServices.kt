package com.route.news.api

import com.route.news.api.model.ArticleResponse
import com.route.news.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET(value = "/v2/top-headlines/sources")
    suspend fun getResources(
        @Query(value = "apiKey") apiKey: String,
        @Query(value = "category") category: String
    ): SourcesResponse

    @GET(value = "/v2/everything")
    suspend fun getArticles(
        @Query(value = "apiKey") apiKey: String,
        @Query(value = "sources") sourceID: String = "",
        @Query(value = "q") searchText: String = ""
    ): ArticleResponse

}