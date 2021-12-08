package com.example.scheduleapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityLoginBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


class LoginActivity: AppCompatActivity() {
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var binding: ActivityLoginBinding

    // [START declare_auth]
    private val mAuth: FirebaseAuth? = null
// [END declare_auth]

    // [END declare_auth]
    // [START declare_auth_listener]
    private val mAuthListener: AuthStateListener? = null
    // [END declare_auth_listener]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        binding.authorizationButton.setOnClickListener {
            var intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.guestButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, GuestActivity::class.java)
            startActivity(intent)
        }
    }
}