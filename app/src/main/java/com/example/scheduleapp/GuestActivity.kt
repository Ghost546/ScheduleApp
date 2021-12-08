package com.example.scheduleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityGuestBinding
import com.example.scheduleapp.databinding.ActivityLoginBinding

class GuestActivity: AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}