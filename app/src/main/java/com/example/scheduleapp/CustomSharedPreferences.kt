package com.example.scheduleapp

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putString
import androidx.core.content.edit

class CustomSharedPreferences(val context: Context){
    private val PREFS_NAME = "mySettingsInScheduleApp"
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(KEY_NAME: String, value: String) {
        sharedPref.edit{ putString(KEY_NAME, value) }
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun removeValue(KEY_NAME: String) {
        sharedPref.edit{remove(KEY_NAME)}
    }
}