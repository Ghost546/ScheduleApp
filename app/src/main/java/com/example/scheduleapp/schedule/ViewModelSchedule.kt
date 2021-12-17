package com.example.scheduleapp.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.scheduleapp.*
import com.example.scheduleapp.mainactivity.LiveDataStudent

class ViewModelSchedule: ViewModel(), IMyCallBack, ITagForLog {
    var liveDataStudent = LiveDataStudent

    var modelSchedule = ModelSchedule(this)

    var listSchedule:MutableList<ScheduleItem>?=null
    var listClub:MutableList<Club>?=null
    var listCallSchedule:MutableList<CallSchedule>?=null
    var listDayOfWeek:MutableList<DayOfWeek>?=null

    fun settingDB(email:String) {
        modelSchedule.setFullNameFromDB(email)
    }


    override fun setFromModelData() {
        listSchedule = modelSchedule.listSchedule
        listClub = modelSchedule.listClub
        listCallSchedule = modelSchedule.listCallSchedule
        listDayOfWeek = modelSchedule.listDayOfWeek
        if (listSchedule?.size?:0>0) {
            Log.i(tag, "!данные в scheduleList = ${listSchedule!![0].weekday} | ${listSchedule!![0].timeStart} | ${listSchedule!![0].subject} | ${listSchedule!![0].place}")
        }
        if (listClub?.size?:0>0) {
            Log.i(tag, "!данные в listClub = ${listClub!![0].clubID} | ${listClub!![0].name} | ${listClub!![0].tutor} | ${listClub!![0].specialty}")
        }
        if (listCallSchedule?.size?:0>0) {
            Log.i(tag, "!данные в listCallSchedule = ${listCallSchedule!![0].callScheduleID} | ${listCallSchedule!![0].timeStart} | ${listCallSchedule!![0].timeFinish}")
        }
        if (listDayOfWeek?.size?:0>0) {
            Log.i(tag, "!данные в listDayOfWeek = ${listDayOfWeek!![0].dayOfWeekID} | ${listDayOfWeek!![0].name}")
        }
    }
}