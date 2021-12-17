package com.example.scheduleapp.mainactivity

import androidx.lifecycle.ViewModel
import com.example.scheduleapp.IMyCallBack
import com.example.scheduleapp.Student

class ViewModelMainActivity:ViewModel(), IMyCallBack {
    var liveDataStudent = LiveDataStudent

    var modelMainActivity = ModelMainActivity(this)

//    fun setEmailForGetData(email: String) {
//        modelMainActivity.setFullNameFromDB(email)
//    }

    override fun setFromModelData() {
        liveDataStudent.postValue(modelMainActivity.student)
    }
}