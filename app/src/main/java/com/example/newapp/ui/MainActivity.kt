package com.example.newapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newapp.R
import com.example.newapp.remote.HolidayRetrofitImpl
import com.example.newapp.repository.MainRepository
import com.example.newapp.viewmodel.MainViewModel
import com.example.newapp.viewmodel.RecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //TODO Add the adapter to the main activity, create it right before the ViewModel
        recyclerViewAdapter = RecyclerViewAdapter()
        mainViewModel =
            MainViewModel(
                mainRepository = MainRepository(
                    retrofitImpl = HolidayRetrofitImpl
                )
            )

        //TODO call the adapter update method with the holidays to pass
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