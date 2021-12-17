package com.example.scheduleapp.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.scheduleapp.CustomSharedPreferences
import com.example.scheduleapp.ITagForLog
import com.example.scheduleapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ITagForLog {
    private lateinit var customSharedPreferences: CustomSharedPreferences
    val viewModelMainActivity: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customSharedPreferences = CustomSharedPreferences(context = this)
//        customSharedPreferences.getValueString("email")?.let {
//            viewModelMainActivity.setEmailForGetData(it)
//        }
        val navView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.my_nav_host)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.scheduleFragment,
            R.id.profileFragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }

}

