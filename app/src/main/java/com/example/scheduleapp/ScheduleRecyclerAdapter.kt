package com.example.scheduleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScheduleRecyclerAdapter(private val scheduleList:List<ScheduleItem>) : RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var weekday:TextView
        var order:TextView
        var time:TextView
        var subject:TextView
        var place:TextView
        var educator:TextView
        init {
            weekday = itemView.findViewById(R.id.weekday_text)
            order = itemView.findViewById(R.id.order_text)
            time = itemView.findViewById(R.id.time_text)
            subject = itemView.findViewById(R.id.subject_text)
            place = itemView.findViewById(R.id.place_text)
            educator = itemView.findViewById(R.id.educator_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_to_schedule, parent, false)
        return  ScheduleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        if (scheduleList[position].isWeekDay) {
            holder.weekday.visibility = View.VISIBLE
            holder.weekday.text = scheduleList[position].weekday
        }
        if (!scheduleList[position].isWeekDay){
            holder.order.visibility = View.VISIBLE
            holder.order.text = scheduleList[position].order
            holder.time.visibility = View.VISIBLE
            holder.time.text = scheduleList[position].time
            holder.subject.visibility = View.VISIBLE
            holder.subject.text = scheduleList[position].subject
            holder.place.visibility = View.VISIBLE
            holder.place.text = scheduleList[position].place
            holder.educator.visibility = View.VISIBLE
            holder.educator.text = scheduleList[position].educator
        }
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}