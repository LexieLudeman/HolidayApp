package com.example.newapp.repository

import com.example.newapp.common.PublicHoliday
import com.example.newapp.remote.HolidayRetrofitImpl

class MainRepository (

    private val retrofitImpl: HolidayRetrofitImpl
) {
    suspend fun getHolidays(): List<PublicHoliday> {
        println("Hello from MainRepository")
        val response = retrofitImpl.instance.getPublicHolidays()
        if (response.isSuccessful && response.body() != null) {
            println("Hello; our response is ${response.body()!!}")
            // todo transform network data into our new PublicHoliday data class

            return response.body()!!.map {
                PublicHoliday(
                    name = it.name,
                    date = it.name
                )
            }
        } else {
            return listOf(
                PublicHoliday(
                    name = "Error",
                    date = ""
                )
            )
        }
    }
}