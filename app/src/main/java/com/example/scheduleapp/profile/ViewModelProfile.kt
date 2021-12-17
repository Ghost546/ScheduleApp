package com.example.scheduleapp.profile

import androidx.lifecycle.ViewModel
import com.example.scheduleapp.IMyCallBack
import com.example.scheduleapp.mainactivity.LiveDataStudent
import com.example.scheduleapp.Student

class ViewModelProfile: ViewModel(), IMyCallBack {
    var modelProfile = ModelProfile(this)

    var liveDataStudent = LiveDataStudent

    fun setLiveDataStudent(student: Student) {
        liveDataStudent.postValue(student)
    }

    override fun setFromModelData() {
        TODO("Not yet implemented")
    }
}