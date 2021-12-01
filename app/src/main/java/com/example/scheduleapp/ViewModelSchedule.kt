package com.example.scheduleapp

import androidx.lifecycle.ViewModel

class ViewModelSchedule: ViewModel() {
    var scheduleList:List<ScheduleItem>?=null
    var testScheduleList:TestScheduleList=TestScheduleList()
    fun setScheduleList() {
        scheduleList = testScheduleList.scheduleList
    }
}