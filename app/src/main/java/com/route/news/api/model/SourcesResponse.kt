package com.route.news.api.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SourcesResponse(

    @SerializedName("sources")
    val sources: List<Source?>? = null,

    @SerializedName("code")
    val code: String? = null,

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("status")
    val status: String? = null
)


