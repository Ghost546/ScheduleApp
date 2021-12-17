package com.example.scheduleapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduleapp.databinding.ActivityGuestBinding
import com.example.scheduleapp.databinding.ActivityGuestClubBinding
import com.example.scheduleapp.databinding.ActivityLoginBinding

class GuestClubActivity:AppCompatActivity() {

    val viewModelGuestClub: ViewModelGuestClub by viewModels()

    private lateinit var binding: ActivityGuestClubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModelFromPreviouslyActivity()

        setDataToView()
    }

    fun setDataToView() {
        binding.clubNameText.text = viewModelGuestClub.club?.name
        binding.clubSpecialityText.text = viewModelGuestClub.club?.specialty
        binding.clubTutorText.text = viewModelGuestClub.club?.tutor
        binding.clubLeaderText.text = viewModelGuestClub.club?.clubLeader
        binding.clubDescriptionText.text = viewModelGuestClub.club?.description
        binding.clubThemesText.text = viewModelGuestClub.club?.themes
    }

    fun setViewModelFromPreviouslyActivity() {
        var args: Bundle? = intent.extras
        viewModelGuestClub.club = args?.getSerializable(Club::class.java.simpleName) as Club
    }
}