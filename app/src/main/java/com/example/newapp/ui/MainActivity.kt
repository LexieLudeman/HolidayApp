package com.example.newapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newapp.HolidayApplication
import com.example.newapp.R
import com.example.newapp.data.remote.HolidayRetrofitImpl
import com.example.newapp.repository.MainRepository
import com.example.newapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: PublicHolidaysAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel =
            MainViewModel(
                mainRepository = MainRepository(
                    retrofitImpl = HolidayRetrofitImpl,
                    holidaysDao = (applicationContext as HolidayApplication).database.publicHolidaysDao()
                )
            )

        adapter = PublicHolidaysAdapter(
            allHolidays = emptyList(),
            mainViewModel = mainViewModel,
            lifecycleOwner = this
        )
        recyclerView = findViewById(R.id.rvHolidays)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        mainViewModel.publicHolidays.observe(this) { publicHolidays ->
            println("Hello: view data is here! $publicHolidays")
            adapter.updateItems(holidays = publicHolidays)
        }

        getPublicHolidays()
        getPrivateHolidays()
    }

    //only has visibility to ViewModel
    private fun getPublicHolidays() {
        println("Hello from MainActivity")
        mainViewModel.getPublicHolidays()
    }

    private fun getPrivateHolidays() {
        println("Hello from MainActivity")
        mainViewModel.getPrivateHolidays()
    }
}