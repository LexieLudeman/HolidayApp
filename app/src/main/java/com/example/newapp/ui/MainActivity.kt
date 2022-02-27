package com.example.newapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newapp.R
import com.example.newapp.remote.HolidayRetrofitImpl
import com.example.newapp.repository.MainRepository
import com.example.newapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel =
            MainViewModel(
                mainRepository = MainRepository(
                    retrofitImpl = HolidayRetrofitImpl
                )
            )
        mainViewModel.publicHoliday.observe(this) {
            println("Hello: view data is here! $it")
        }
        getHolidays()
    }

    //only has visibility to ViewModel

    private fun getHolidays() {
        println("Hello from MainActivity")
        mainViewModel.getHolidays()
    }
}