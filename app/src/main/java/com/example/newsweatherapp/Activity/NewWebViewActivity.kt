package com.example.newsweatherapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.newsweatherapp.R


class NewWebViewActivity : AppCompatActivity() {

    private lateinit var webview: WebView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_new_web_view)


        val intent = intent
        val URL = intent.extras!!.getString("URL")
        webview = findViewById<WebView>(R.id.webView)
        webview.loadUrl(URL!!)
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = WebViewClient()

    }
}