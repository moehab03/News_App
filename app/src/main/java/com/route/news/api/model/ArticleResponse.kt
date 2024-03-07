package com.route.news.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleResponse(
    @SerializedName("code")
    val code: String? = null,

    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: List<Article?>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("message")
    val message: String? = null,
)


