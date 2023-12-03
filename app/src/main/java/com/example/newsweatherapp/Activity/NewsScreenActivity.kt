package com.example.newsweatherapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsweatherapp.Model.NewsListResponseModel
import com.example.newsweatherapp.R
import com.example.newsweatherapp.Rest.ApiClient
import com.example.newsweatherapp.Rest.ApiInterface
import com.example.newsweatherapp.adapter.NewsListAdapter
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsScreenActivity : AppCompatActivity() {

    private lateinit var newsListRecyclerView: RecyclerView
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_news_screen)

        newsListRecyclerView = findViewById<RecyclerView>(R.id.newsListRecyclerView)

        newsListView()

    }
    fun newsListView(){

        val params: MutableMap<String, String> = HashMap()
        params.put("format", "json")
        params.put("limit", "20")
        params.put("offset", "0")

        val apiService: ApiInterface = ApiClient.getResponse().create<ApiInterface>(
            ApiInterface::class.java
        )
        val call: Call<NewsListResponseModel> = apiService.NewAppResponse(params.toString())
        Log.e("search-category-type", "" + params)
        call.enqueue(object : Callback<NewsListResponseModel> {
            override fun onResponse(
                call: Call<NewsListResponseModel>,
                response: Response<NewsListResponseModel>
            ) {

                val statusCode = response.code()
                Log.d("dldsnfdnvdnv", "" + statusCode)

                if (statusCode == 200) {

                    Log.e("NewsList", Gson().toJson(response.body()))
                    Log.d("sjdbdbvbdfvbdvbsb", "" + response.body()!!.getResult()!!.size)

                    newsListAdapter = NewsListAdapter(this@NewsScreenActivity, response.body()!!.getResult()!!)
                    val layoutManagerpayment = LinearLayoutManager(applicationContext,
                        LinearLayoutManager.VERTICAL,false)
                    newsListRecyclerView.layoutManager = layoutManagerpayment
                    newsListRecyclerView.adapter = newsListAdapter
                    newsListAdapter.notifyDataSetChanged()



                }
            }

            override fun onFailure(call: Call<NewsListResponseModel>, t: Throwable) {

            }
        })


    }
}