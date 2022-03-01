package com.example.newapp.data.remote

import com.google.gson.annotations.SerializedName

//HolidayResponse has to take the raw json and parse to kotlin classes (JSON to Kotlin)
class HolidayResponse: ArrayList<HolidayResponse.HolidayItem>() {
    data class HolidayItem(
        @SerializedName("date")
        val date: String,
        @SerializedName("name")
        val name: String
    )
}