package com.example.scheduleapp

import androidx.lifecycle.ViewModel

class ViewModelFormForSignInAndOutOnClub:ViewModel() {
    var selectedClub: Club? = null
    val clubs = arrayOf(Club("Кружок Java"), Club("Кружок Web-разработки"))
    var clubsString = mutableListOf<String>()
}