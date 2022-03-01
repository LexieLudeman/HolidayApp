package com.example.newapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PublicHolidaysDao {
    // get stuff
    @Query("SELECT * FROM PublicHolidayEntity")
    suspend fun getAllPublicHolidays(): List<PublicHolidayEntity>

    @Query("SELECT COUNT (*) FROM PublicHolidayEntity where name = :name")
    suspend fun checkIfPublicHolidayUnique(name: String): Int

    // insert stuff
    @Insert
    suspend fun insertPublicHolidays(publicHolidays: List<PublicHolidayEntity>)

    @Delete
    suspend fun deleteHoliday(repeatedHolidays: PublicHolidayEntity)
}