package com.example.scheduleapp.mainactivity

import android.util.Log
import com.example.scheduleapp.*
import com.example.scheduleapp.schedule.ScheduleItem

class ModelMainActivity(val iMyCallBack: IMyCallBack): ITagForLog {
    var dbConnect = DBConnect()
    var count = 0
    var student=Student()

//    fun setFullNameFromDB(email: String) {
//        Log.i(tag, "!db get email = $email")
//        dbConnect.userEmail = email
//        dbConnect.getStudentViaEmail {
//            Log.i(tag, "!get id = ${it.studentID}")
//            endNameFromDB(it)
//        }
//    }

    fun endNameFromDB(student: Student) {
        if(count==0) {
            this.student = student
        }
        iMyCallBack.setFromModelData()
        count+1
    }
}