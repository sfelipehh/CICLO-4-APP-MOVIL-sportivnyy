package com.source.sportivnyy.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.material.snackbar.Snackbar
import com.source.sportivnyy.R
import com.source.sportivnyy.databinding.ActivityLoginBinding
import kotlinx.coroutines.delay

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,NavigationDrawer::class.java)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        login.setOnClickListener { view ->
            loading.visibility = View.VISIBLE
            Snackbar.make(view, "Bienvenido ${username.text}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            startActivity(intent)
            finish()
        }
    }
}