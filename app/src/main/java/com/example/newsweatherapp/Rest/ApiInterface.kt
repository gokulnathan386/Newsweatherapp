package com.example.newsweatherapp.Rest

import retrofit2.http.GET
import com.example.newsweatherapp.Model.NewsListResponseModel
import retrofit2.Call
import retrofit2.http.Query

interface ApiInterface {
    @GET("articles/")
    fun NewAppResponse(@Query("params") params: String?): Call<NewsListResponseModel>
}