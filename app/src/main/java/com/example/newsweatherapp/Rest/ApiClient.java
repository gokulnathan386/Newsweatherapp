package com.example.newsweatherapp.Rest;


import android.util.Log;


import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String Domain_Live="https://api.spaceflightnewsapi.net/v4/";


    public static final String BASE_URL = Domain_Live;

    private static Retrofit retrofit = null;


    public static Retrofit getResponse() {
        final okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Log.e("ctdctftygfyhuihjio",""+retrofit.baseUrl());
        }
        return retrofit;
    }




}






