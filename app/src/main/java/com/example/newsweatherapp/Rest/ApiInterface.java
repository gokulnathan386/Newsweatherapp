package com.example.newsweatherapp.Rest;


import com.example.newsweatherapp.Model.NewsListResponseModel;


import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("articles/")
    Call<NewsListResponseModel> NewAppResponse(@Query("params") String params);


}
