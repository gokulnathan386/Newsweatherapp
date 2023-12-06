package com.example.newsweatherapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var  layoutManagerpayment:LinearLayoutManager
    private lateinit var searchView: SearchView
    private lateinit var nextbtn: Button
    private lateinit var privbtn: Button
    private var offset = 0
    private var limit = 20
    private var k:Int = 1
    var nextlimit: String? = null
    var nectoffset:String? = null

    var previouslimit:String? = null
    var previousoffset:String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_news_screen)

        newsListRecyclerView = findViewById<RecyclerView>(R.id.newsListRecyclerView)
        searchView = findViewById<SearchView>(R.id.searchView)
        nextbtn = findViewById<Button>(R.id.nextbtn)
        privbtn = findViewById<Button>(R.id.privbtn)

        newsListView()


        nextbtn.setOnClickListener{
            limit = nextlimit!!.toInt()
            offset = nectoffset!!.toInt()
            k=1
            newsListView()
        }

        privbtn.setOnClickListener{
            Log.d("dvmdvdvmdfmvf","" + previousoffset)
            limit = previouslimit!!.toInt()
            offset = previousoffset!!.toInt()
            newsListView()
        }


    }
    fun newsListView() {

        val params: MutableMap<String, String> = HashMap()
        if(k == 0){
            params["format"] = "json"
            params["limit"] = limit.toString()
        }else{
            params["format"] = "json"
            params["limit"] = limit.toString()
            params["offset"] = offset.toString()
        }




        val apiService: ApiInterface = ApiClient.response!!.create(ApiInterface::class.java)
        val call: Call<NewsListResponseModel> = apiService.NewAppResponse(params)
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

                    if(response.body()!!.getNext() != null){
                        val url = response.body()!!.getNext()
                        val limit1 = getParameterValue(url!!, "limit")
                        val offset1 = getParameterValue(url!!, "offset")
                        nextlimit = limit1!!
                        nectoffset = offset1!!
                        nextbtn.visibility = View.VISIBLE

                    }else{
                        nextbtn.visibility =View.GONE
                        privbtn.visibility = View.VISIBLE
                    }

                    if(response.body()!!.getPrevious() != null){
                        val url = response.body()!!.getPrevious()
                        val limit1 = getParameterValue(url!!, "limit")
                        val offset1 = getParameterValue(url!!, "offset")


                        previouslimit = limit1!!
                        if(offset1 != null){
                            previousoffset = offset1!!
                            k = 1
                        }else{
                            k = 0
                        }
                        privbtn.visibility =View.VISIBLE

                    }else{
                        privbtn.visibility =View.GONE
                        nextbtn.visibility = View.VISIBLE
                    }

                        newsListAdapter = NewsListAdapter(this@NewsScreenActivity, response.body()!!.getResult()!!,response.body()!!.getResult()!!,searchView)
                        layoutManagerpayment = LinearLayoutManager(this@NewsScreenActivity,
                            LinearLayoutManager.VERTICAL,false)
                        newsListRecyclerView.layoutManager = layoutManagerpayment
                        newsListRecyclerView.adapter = newsListAdapter

                }
            }

            override fun onFailure(call: Call<NewsListResponseModel>, t: Throwable) {

            }
        })

    }

    fun getParameterValue(url: String, parameterName: String): String? {
        val regex = Regex("[?&]$parameterName=([^&]+)")
        val matchResult = regex.find(url)
        return matchResult?.groupValues?.get(1)
    }
}