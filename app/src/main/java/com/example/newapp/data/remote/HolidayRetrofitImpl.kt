package com.example.newapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object HolidayRetrofitImpl{

    lateinit var instance: HolidayRetrofit

    init {
        create()
    }

    private fun create(): HolidayRetrofit {
        // complete url: https://date.nager.at/api/v3/publicholidays/2022/US
        val baseURL = "https://date.nager.at/api/v3/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(HolidayRetrofit::class.java)
        return instance

    }
}
