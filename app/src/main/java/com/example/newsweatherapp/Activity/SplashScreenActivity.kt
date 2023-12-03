package com.example.newsweatherapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.newsweatherapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashImageView = findViewById<ImageView>(R.id.splashImageView)
        splashImageView.alpha = 0f
        splashImageView.animate().setDuration(1500).alpha(1f).withEndAction{
            val k = Intent(this,MainActivity::class.java)
            startActivity(k)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
}