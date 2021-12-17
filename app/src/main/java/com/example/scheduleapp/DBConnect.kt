package com.example.scheduleapp

import android.util.Log
import com.example.scheduleapp.schedule.ScheduleItem
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DBConnect:ITagForLog {
    val db = Firebase.firestore
    var userEmail:String?=null
    var student = Student()

    var club = Club()
    var listClub:MutableList<Club> = mutableListOf()
    var listSchedule:MutableList<ScheduleItem> = mutableListOf()
    var listCallSchedule:MutableList<CallSchedule> = mutableListOf()
    var listDayOfWeek:MutableList<DayOfWeek> = mutableListOf()

    fun getStudentViaEmail(myCallback: (Student) -> Unit) {
        db.collection("Student").whereEqualTo("email", "$userEmail").get().addOnSuccessListener { it ->
            it.forEach { it ->
                Log.i(tag, "!${it.get("firstName")} ${it.get("secondName")} ${it.get("lastName")}, ${it.get("specialtyID")}")
                student.firstName = it.get("firstName").toString()
                student.secondName = it.get("secondName").toString()
                student.lastName = it.get("lastName").toString()
                student.group = it.get("group").toString()
                student.email = it.get("email").toString()
                student.specialtyID = it.get("specialtyID").toString()
                student.studentID = it.get("studentID").toString()

            }
            myCallback(student)
        }


    }

    fun findClubsWithAuthStudent(myCallback: (MutableList<Club>) -> Unit) {
        Log.i(tag, "!вызов findClubsWithAuthStudent Получен ${student.studentID}")
        db.collection("ClubGroup").whereArrayContainsAny("studentsID", listOf(student.studentID)).get().addOnSuccessListener { it ->
            if(it!= null){
                Log.i(tag, "!размер ${it.size()}")
                if (it.size()!=0){
                    for (document in it) {
                        Log.i(tag, "!${document.id} => ${document.data} ${document.get("clubID")}")
                        club.name = document.get("name").toString()
                        club.description = document.get("description").toString()
                        club.clubID = document.get("clubID").toString()
                        club.tutor = document.get("tutor").toString()
                        listClub.add(club)
                        myCallback(listClub)
                    }
                }
            }
        }
    }

    fun setSchedules(myCallback: (MutableList<ScheduleItem>) -> Unit) {
        Log.i(tag, "!вызов setSchedule")
        for (i in listClub.iterator()) {
            db.collection("Schedule").whereEqualTo("clubID", i.clubID).get().addOnSuccessListener {
                for (document in it) {
                    Log.i(tag, "!${document.id} => ${document.data}")
                    var scheduleItem = ScheduleItem()
                    scheduleItem.subjectID= document.get("clubID").toString()
                    scheduleItem.timeID = document.get("callSchedule").toString()
                    scheduleItem.weekdayID = document.get("dayOfWeekID").toString()
                    scheduleItem.place = document.get("venue").toString()
                    if (!listSchedule.contains(scheduleItem)) {
                        listSchedule.add(scheduleItem)
                        myCallback(listSchedule)
                    }
                }
            }
        }
    }

    fun setScheduleWithClub(myCallback: (MutableList<Club>) -> Unit) {
        for(i in listClub.iterator()) {
            db.collection("Club").whereEqualTo("clubID", i.clubID).get().addOnSuccessListener {
                for (document in it) {
                    Log.i(tag, "!${document.id} => ${document.data}")
                    i.name = document.get("name").toString()
                    i.tutor = document.get("tutor").toString()
                    myCallback(listClub)
                }
            }
        }
    }

    fun setScheduleWithTime(myCallback: (MutableList<ScheduleItem>) -> Unit) {
        Log.i(tag, "!вызов setScheduleWithTime")
        for (i in listSchedule.iterator()) {
            db.collection("CallSchedule").whereEqualTo("callScheduleID", i.timeID).get().addOnSuccessListener {
                for (document in it) {
                    Log.i(tag, "!${document.id} => ${document.data}")
                    var callSchedule = CallSchedule()
                    i.timeID = document.get("callScheduleID").toString()
                    i.timeStart = document.get("TimeStart").toString()
                    i.timeFinish = document.get("TimeFinish").toString()
                    myCallback(listSchedule)
                }
            }
        }
    }

    fun setScheduleWithDayOfWeek(myCallback: (MutableList<ScheduleItem>) -> Unit) {
        Log.i(tag, "!вызов setScheduleWithDayOfWeek")
        for (i in listSchedule.iterator()) {
            db.collection("DayOfWeek").whereEqualTo("dayOfWeekID", i.weekdayID).get().addOnSuccessListener {
                Log.i(tag, "!dayOfWeekID = ${i.weekdayID}")
                    it.forEach { it ->
                        Log.i(tag, "!${it.id} => ${it.data}")
                        i.weekdayID = it.get("dayOfWeekID").toString()
                        i.weekday = it.get("name").toString()

                        myCallback(listSchedule)

                    }
                }
        }
    }

}