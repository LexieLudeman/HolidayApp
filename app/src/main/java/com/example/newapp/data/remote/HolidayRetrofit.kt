package com.example.newapp.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HolidayRetrofit {

    @GET("PublicHolidays/2022/US")
    suspend fun getPublicHolidays(): Response<HolidayResponse>

}

//	[
//   {
//      "date": "2017-01-01",
//      "localName": "Neujahr",
//      "name": "New Year's Day",
//      "countryCode": "AT",
//      "fixed": true,
//      "global": true,
//      "counties": null,
//      "launchYear": 1967,
//      "types": [
//         "Public"
//      ]
//   }
//]

