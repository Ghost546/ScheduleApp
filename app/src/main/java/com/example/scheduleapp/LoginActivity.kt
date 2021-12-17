package com.example.scheduleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityLoginBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseUser
import android.text.TextUtils
import android.widget.Toast
import com.example.scheduleapp.mainactivity.MainActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var customSharedPreferences: CustomSharedPreferences
    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var binding: ActivityLoginBinding

    private var mAuth: FirebaseAuth? = null

    private var mAuthListener: AuthStateListener? = null

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }

    override fun onDestroy() {
        moveTaskToBack(true);
        super.onDestroy()
        System.runFinalizersOnExit(true);
        System.exit(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customSharedPreferences = CustomSharedPreferences(context = this)
        mAuth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        mAuthListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                Log.i("LoginActivity", "!onAuthStateChanged:signed_in:" + user.uid)
            } else {
                // User is signed out
                Log.i("LoginActivity", "!onAuthStateChanged:signed_out")
            }
            updateUI(user)
        }
        setContentView(binding.root)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        binding.guestButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, GuestActivity::class.java)
            startActivity(intent)
        }

        binding.authorizationButton.setOnClickListener {
            signIn(binding.LoginEditText.editText?.text.toString(), binding.PasswordEditText.editText?.text.toString())
        }

    }

    private fun setEmail() {
        if(customSharedPreferences.getValueString("email") == null) {
            customSharedPreferences.saveString("email", binding.LoginEditText.editText?.text.toString())
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            setEmail()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateForm(): Boolean {
        var valid = true
        val email: String = binding.LoginEditText.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.LoginEditText.editText?.error = "Требуется"
            valid = false
        } else {
            binding.LoginEditText.editText?.error = null
        }
        val password: String = binding.PasswordEditText.editText?.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.PasswordEditText.editText?.error = "Требуется"
            valid = false
        } else {
            binding.PasswordEditText.editText?.error = null
        }
        return valid
    }

    private fun signIn(email: String, password: String) {
        Log.i("LoginActivity", "!signIn:$email")
        if (!validateForm()) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                Log.i("LoginActivity", "!signInWithEmail:onComplete:" + task.isSuccessful)
                if (!task.isSuccessful) {
                    Log.i("LoginActivity", "!signInWithEmail:failed", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Попытка входа завершилась неудачно",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}