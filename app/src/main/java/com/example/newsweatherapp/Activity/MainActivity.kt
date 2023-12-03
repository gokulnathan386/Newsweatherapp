package com.example.newsweatherapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.newsweatherapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val phase1 = findViewById<Button>(R.id.Phase1)
        val phase2 = findViewById<Button>(R.id.Phase2)

        phase1.setOnClickListener{
            val k = Intent(this,NewsScreenActivity::class.java)
            startActivity(k)
        }

        phase2.setOnClickListener{
            val k = Intent(this,NewsScreenActivity::class.java)
            startActivity(k)
        }




    }
}