package com.example.scheduleapp.schedule

import android.util.Log
import com.example.scheduleapp.*

class ModelSchedule(val iMyCallBack: IMyCallBack):ITagForLog {
    var dbConnect = DBConnect()
    var student = Student()

    var listClub:MutableList<Club> = mutableListOf()
    var listSchedule:MutableList<ScheduleItem> = mutableListOf()
    var listCallSchedule:MutableList<CallSchedule> = mutableListOf()
    var listDayOfWeek:MutableList<DayOfWeek> = mutableListOf()

    fun setFullNameFromDB(email: String) {
        Log.i(tag, "!db get email = $email")
        dbConnect.userEmail = email
        dbConnect.getStudentViaEmail {
            Log.i(tag, "!get id = ${it.studentID}")
            student = it
            setShedule()
        }

    }

    fun setShedule() {
        Log.i(tag, "!вызов setStudentsClubs")
        dbConnect.findClubsWithAuthStudent {
            listClub = it
            dbConnect.setSchedules {
                listSchedule = it
                dbConnect.setScheduleWithClub{
                    dbConnect.setScheduleWithTime {
                        listSchedule = it
                        dbConnect.setScheduleWithDayOfWeek{
                            listSchedule = it
                            iMyCallBack.setFromModelData()
                        }
                    }
                }
            }
        }
    }
}