package com.example.newapp.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.common.PublicHoliday

class RecyclerViewAdapter(private val list: List<PublicHoliday>)
    : RecyclerView.Adapter<PublicHolidayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicHolidayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PublicHolidayViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PublicHolidayViewHolder, position: Int) {
        val publicHoliday: PublicHoliday = list[position]
        holder.bind(publicHoliday)
    }

    override fun getItemCount(): Int = list.size

    fun updateItems(holidays: List<PublicHoliday>) {
        //TODO Public method inside adapter to update the items.
    }

}

class PublicHolidayViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.recyclerview_row, parent, false)) {

    private var mNameView: TextView? = null
    private var mDateView: TextView? = null

    init {
       mNameView = itemView.findViewById(R.id.name)
       mDateView = itemView.findViewById(R.id.date)
    }

    fun bind(publicHoliday: PublicHoliday) {
        mNameView?.text = publicHoliday.name
        mDateView?.text = publicHoliday.date
    }
}