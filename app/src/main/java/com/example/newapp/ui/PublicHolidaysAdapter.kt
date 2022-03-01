package com.example.newapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.R
import com.example.newapp.common.PublicHoliday
import com.example.newapp.viewmodel.MainViewModel

class PublicHolidaysAdapter(
    private var allHolidays: List<PublicHoliday>,
    mainViewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<PublicHolidaysAdapter.PublicHolidayViewHolder>() {

    init {
        mainViewModel.publicHolidays.observe(lifecycleOwner) {updateItems(holidays = it)}
        mainViewModel.privateHolidays.observe(lifecycleOwner) {updateItems(holidays = it)}
    }

    inner class PublicHolidayViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.name)
        private val dateText: TextView = view.findViewById(R.id.date)

        fun bind(publicHoliday: PublicHoliday) {
            nameText.text = publicHoliday.name
            dateText.text = publicHoliday.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicHolidayViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recyclerview_row, parent, false)
        return PublicHolidayViewHolder(view)
    }

    override fun onBindViewHolder(holder: PublicHolidayViewHolder, position: Int) {
        holder.bind(publicHoliday = allHolidays[position])
    }

    override fun getItemCount(): Int = allHolidays.size

    fun updateItems(holidays: List<PublicHoliday>) {
        val oldLastIndex = if(allHolidays.isNotEmpty()) allHolidays.size - 1 else 0
        allHolidays += holidays
        notifyItemRangeChanged(oldLastIndex, holidays.size)
    }

}

