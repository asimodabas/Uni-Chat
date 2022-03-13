package com.asimodabas.uni_chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asimodabas.uni_chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}