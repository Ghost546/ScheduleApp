package com.example.scheduleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduleapp.databinding.ActivityGuestBinding
class GuestActivity: AppCompatActivity(), GetDataFromAdapter, ITagForLog {

    private lateinit var binding: ActivityGuestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listOfClubs: List<Club> = listOf(Club())
        listOfClubs[0].name = "Кружок по языку Java"
        listOfClubs[0].specialty = "Программирование в компьютерных системах"
        listOfClubs[0].description = "Какое то описание"
        listOfClubs[0].tutor = "Гайфутдинов"
        listOfClubs[0].clubLeader = "Сафина"
        listOfClubs[0].themes = "Такие то темы"
        binding.scheduleRecyclerView.adapter = GuestRecyclerAdapter(this, listOfClubs)
        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun startActivityFromAdapter(club: Club) {
        Log.i(tag, "Вызов startActivityFromAdapter")
        val intent = Intent(this@GuestActivity, GuestClubActivity::class.java)
        intent.putExtra(Club::class.java.simpleName, club)
        startActivity(intent)
    }
}