package com.example.newsweatherapp.Rest

import retrofit2.http.GET
import com.example.newsweatherapp.Model.NewsListResponseModel
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("articles/")
    fun NewAppResponse(@QueryMap params: Map<String, String>): Call<NewsListResponseModel>
}