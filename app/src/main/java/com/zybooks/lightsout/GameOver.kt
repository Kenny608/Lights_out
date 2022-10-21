package com.zybooks.lightsout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zybooks.lightsout.databinding.ActivityGameOverBinding
import com.zybooks.lightsout.databinding.ActivityMainBinding

private lateinit var binding:ActivityGameOverBinding

class GameOver : AppCompatActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        }
    }
