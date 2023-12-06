package com.example.newsweatherapp.Rest

import android.accessibilityservice.GestureDescription
import android.util.Log


import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit





object ApiClient {
    const val Domain_Live = "https://api.spaceflightnewsapi.net/v4/"
    const val BASE_URL = Domain_Live
    private var retrofit: Retrofit? = null
    val response: Retrofit?
        get() {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit
        }
}