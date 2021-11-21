package com.source.sportivnyy.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}