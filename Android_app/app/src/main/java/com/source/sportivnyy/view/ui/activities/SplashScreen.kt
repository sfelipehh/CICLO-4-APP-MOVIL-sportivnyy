package com.source.sportivnyy.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val animation = AnimationUtils.loadAnimation(this,R.anim.splashscreen_anim)
        binding.ivSplashScreen.startAnimation(animation)
        val intent = Intent(this,MainActivity::class.java)

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                Toast.makeText(
                    applicationContext,
                    "Bienvenido",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
}