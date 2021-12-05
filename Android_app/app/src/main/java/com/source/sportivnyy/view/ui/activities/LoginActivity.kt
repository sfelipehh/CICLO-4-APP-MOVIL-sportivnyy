package com.source.sportivnyy.view.ui.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import  com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.source.sportivnyy.databinding.ActivityLoginBinding
import com.source.sportivnyy.view.ui.activities.NavigationDrawer as MainActivity
import com.source.sportivnyy.R
import com.source.sportivnyy.model.network.FirebaseServices
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val AUTH_REQUEST_CODE = 1234
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var listener: FirebaseAuth.AuthStateListener
    lateinit var providers: List<AuthUI.IdpConfig>
    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(listener)
    }

    override fun onStop() {
        if (listener != null)
            firebaseAuth.removeAuthStateListener(listener)
        super.onStop()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build()
        )

        val intent = Intent(this, MainActivity::class.java)
        firebaseAuth = FirebaseAuth.getInstance()
        val firestore = FirebaseServices.FirestoreService()
        listener = FirebaseAuth.AuthStateListener{ p0->
            val user = p0.currentUser
            if (user != null){
                val userId = user.uid
                firestore.firebaseFirestore.collection("users").document(userId)
                    .update("carrito",FieldValue.arrayUnion(null)).
                    addOnFailureListener {
                        firestore.firebaseFirestore.
                        collection("users").document(userId)
                            .set(hashMapOf("carrito" to arrayListOf(null)), SetOptions.merge()) }
                startActivity(intent)
            }else{
                startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.logo)
                    .setTheme(R.style.Theme_Sportivnyy_NoActionBar)
                    .build(),AUTH_REQUEST_CODE)
            }
        }

    }
}