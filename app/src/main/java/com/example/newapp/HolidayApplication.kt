package com.example.newapp

import android.app.Application
import androidx.room.Room
import com.example.newapp.data.database.HolidaysDatabase

class HolidayApplication: Application() {

    lateinit var database: HolidaysDatabase

    override fun onCreate() {
        super.onCreate()
        createDatabase()
    }

    private fun createDatabase() {
        database = Room.databaseBuilder(
            applicationContext,
            HolidaysDatabase::class.java, "holidays-database"
        ).build()
    }
}

