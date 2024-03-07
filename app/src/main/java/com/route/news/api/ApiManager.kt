package com.route.news.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object {
        private var retrofit: Retrofit? = null

        fun getWebService(): WebServices {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClientCreate())
                    .build()
            }
            return retrofit!!.create(WebServices::class.java)
        }

        private fun loggingInterceptorCreate(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor { message ->
                Log.e("ApiManager", "body => $message")
            }
        }

        private fun okHttpClientCreate(): OkHttpClient {
            return OkHttpClient().newBuilder().addInterceptor(loggingInterceptorCreate()).build()
        }
    }
}
