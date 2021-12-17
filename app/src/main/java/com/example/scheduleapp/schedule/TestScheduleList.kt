package com.example.scheduleapp.schedule

import com.example.scheduleapp.schedule.ScheduleItem

class TestScheduleList {
    var scheduleList: List<ScheduleItem>
    var scheduleItem1: ScheduleItem = ScheduleItem() //0, weekday = "Понедельник", isWeekDay = true
    var scheduleItem2: ScheduleItem = ScheduleItem() //1, time = "8:00", subject = "Кружок Web-разработки", place = "305", educator = "Назиманов"
    var scheduleItem3: ScheduleItem = ScheduleItem() //2, time = "9:20", subject = "Кружок Java-разработки", place = "Zoom", educator = "Гайфутдинов"
    init {
        scheduleList = listOf(scheduleItem1, scheduleItem2, scheduleItem3)
    }
}