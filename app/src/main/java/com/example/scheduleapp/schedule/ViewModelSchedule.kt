package com.example.scheduleapp.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.scheduleapp.*
import com.example.scheduleapp.mainactivity.LiveDataStudent

class ViewModelSchedule: ViewModel(), IMyCallBack, ITagForLog {
    var liveDataStudent = LiveDataStudent

    var modelSchedule = ModelSchedule(this)

    var listSchedule:MutableList<ScheduleItem>?=null
    var listClub:MutableList<Club> = mutableListOf()
    var listCallSchedule:MutableList<CallSchedule>?=null
    var listDayOfWeek:MutableList<DayOfWeek>?=null
    var scheduleArrayForView:ArrayList<ScheduleItem> = arrayListOf()

    fun settingDB(email:String) {
        modelSchedule.setFullNameFromDB(email)
    }

    fun setScheduleArrayForView() {
        if (listSchedule!=null) {
            for(i in listSchedule?.indices!!) {
                var scheduleItem = ScheduleItem()
                scheduleItem.timeStart = listSchedule!![i].timeStart
                scheduleItem.weekday = listSchedule!![i].weekday
                scheduleItem.place = listSchedule!![i].place
                for (j in listClub.indices) {
                    if (listClub[j].clubID?.equals(listSchedule!![i].subjectID) == true) {
                        scheduleItem.subject = listClub[j].name
                        scheduleItem.educator = listClub[j].tutor
                    }
                }
                when(scheduleItem.weekday) {
                    "Понедельник" -> scheduleItem.weekdayIDForSort = 0
                    "Вторник" -> scheduleItem.weekdayIDForSort = 1
                    "Среда" -> scheduleItem.weekdayIDForSort = 2
                    "Четверг" -> scheduleItem.weekdayIDForSort = 3
                    "Пятница" -> scheduleItem.weekdayIDForSort = 4
                    "Суббота" -> scheduleItem.weekdayIDForSort = 5
                }
                scheduleArrayForView.add(scheduleItem)
                if (scheduleArrayForView.size>0) {
                    Log.i(tag, "!данные в scheduleArrayForView = ${scheduleArrayForView[0].weekday} | ${scheduleArrayForView[0].timeStart} | ${scheduleArrayForView[0].subject} | ${scheduleArrayForView[0].place} | ${scheduleArrayForView[0].educator}")
                }
            }
        }


    }


    override fun setFromModelData() {
        listSchedule = modelSchedule.listSchedule
        listClub = modelSchedule.listClub
        listCallSchedule = modelSchedule.listCallSchedule
        listDayOfWeek = modelSchedule.listDayOfWeek
        if (listSchedule?.size?:0>0) {
            Log.i(tag, "!данные в scheduleList = ${listSchedule!![0].weekday} | ${listSchedule!![0].timeStart} | ${listSchedule!![0].subjectID} | ${listSchedule!![0].place} ")
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
        setScheduleArrayForView()
    }
}