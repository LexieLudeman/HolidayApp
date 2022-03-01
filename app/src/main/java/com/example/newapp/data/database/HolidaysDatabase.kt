package com.example.newapp.data.database

import androidx.room.*

@Database(
    entities = [PublicHolidayEntity::class],
    version = 1
)
abstract class HolidaysDatabase : RoomDatabase() {
    abstract fun publicHolidaysDao(): PublicHolidaysDao
}

