package com.example.newapp.repository

import com.example.newapp.common.PublicHoliday
import com.example.newapp.data.database.PublicHolidayEntity
import com.example.newapp.data.database.PublicHolidaysDao
import com.example.newapp.data.remote.HolidayRetrofitImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val retrofitImpl: HolidayRetrofitImpl,
    private val holidaysDao: PublicHolidaysDao
) {
    suspend fun getPublicHolidays(): List<PublicHoliday> {
        println("Hello from MainRepository")
        val response = retrofitImpl.instance.getPublicHolidays()
        if (response.isSuccessful && response.body() != null) {
            println("Hello; our response is ${response.body()!!}")

            val publicHolidays = response.body()!!.map {
                PublicHoliday(
                    name = it.name,
                    date = it.date
                )
            }.distinctBy {
                it.name
            }
            insertHolidays(holidays = publicHolidays)
            return publicHolidays
        } else {
            return listOf(
                PublicHoliday(
                    name = "Error",
                    date = ""
                )
            )
        }
    }

    fun getPrivateHolidays(): List<PublicHoliday> {
        return listOf(
            PublicHoliday(
                name = "My birthday",
                date = "2022-10-18"
            ),
            PublicHoliday(
                name = "Brian's birthday",
                date = "2022-06-27"
            )
        )
    }

    private suspend fun getHolidaysFromDatabase(): List<PublicHoliday> {
        val entities: List<PublicHolidayEntity> = holidaysDao.getAllPublicHolidays()
        val publicHolidays: List<PublicHoliday> = entities.map {
            PublicHoliday(
                name = it.name,
                date = it.date
            )
        }
        return publicHolidays
    }

    private suspend fun insertHolidays(holidays: List<PublicHoliday>) {
        val uniqueHolidays = holidays.filter {
            holidaysDao.checkIfPublicHolidayUnique(it.name) == 0
        }
//        val entities: List<PublicHolidayEntity> = uniqueHolidays.map {
//            PublicHolidayEntity(
//                name = it.name,
//                date = it.date
//            )
//        }
        holidaysDao.insertPublicHolidays(publicHolidays = uniqueHolidays.map {
            PublicHolidayEntity(
                name = it.name,
                date = it.date
            )
        })
        cleanHolidaysDb()

        with(getHolidaysFromDatabase()) {
            println("Hello: database contains: $this")
        }
    }

    private suspend fun cleanHolidaysDb() {
        val repeatedHolidays = holidaysDao.getAllPublicHolidays().find {
            println("Hello debug: $it")
            holidaysDao.checkIfPublicHolidayUnique(it.name) > 1
        }
        repeatedHolidays?.let {
            holidaysDao.deleteHoliday(it)
            println("Hello you deleted $repeatedHolidays")
            cleanHolidaysDb()
        } ?: println("Not deleted")
    }
}