package com.example.scheduleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuestRecyclerAdapter(var getDataFromAdapter: GetDataFromAdapter, var listOfClubs:List<Club>): RecyclerView.Adapter<GuestRecyclerAdapter.GuestViewHolder>() {
    class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clubNameText: TextView
        var clubSpecialityText: TextView
        init {
            clubNameText = itemView.findViewById(R.id.club_name_text)
            clubSpecialityText = itemView.findViewById(R.id.club_speciality_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val inflater = LayoutInflater.from(parent!!.getContext())
        val view = inflater.inflate(R.layout.item_to_list_of_club, parent, false)
        return GuestViewHolder(view).listen { pos, type ->
            getDataFromAdapter.startActivityFromAdapter(club = listOfClubs[pos])
        }
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.clubNameText.text = listOfClubs[position].name
        holder.clubSpecialityText. text = listOfClubs[position].specialty
    }

    override fun getItemCount(): Int {
        return listOfClubs.size
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
}