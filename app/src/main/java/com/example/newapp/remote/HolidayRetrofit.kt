package com.example.newapp.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HolidayRetrofit {

    @GET("PublicHolidays/2022/US")
    suspend fun getPublicHolidays(): Response<HolidayResponse>

}

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

//HolidayResponse has to take the raw json and parse to kotlin classes (JSON to Kotlin)
class HolidayResponse: ArrayList<HolidayResponse.HolidayItem>() {
    data class HolidayItem(
        @SerializedName("date")
        val date: String,
        @SerializedName("name")
        val name: String
    )
}