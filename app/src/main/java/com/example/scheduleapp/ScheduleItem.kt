package com.example.scheduleapp

import android.widget.TextView

data class ScheduleItem(val id: Int,
                        var isWeekDay: Boolean = false,
                        var weekday: String? = null,
                        var order: String? = null,
                        var time: String? = null,
                        var subject: String? = null,
                        var place: String? = null,
                        var educator: String? = null)