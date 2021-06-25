package com.example.kisshtassignment.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kisshtassignment.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onScreen1Click(view: View) {
        startActivity(Intent(this, Screen1Activity::class.java))
    }

    fun onScreen2Click(view: View) {
        startActivity(Intent(this, Screen2Activity::class.java))
    }
}