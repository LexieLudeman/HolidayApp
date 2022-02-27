package com.example.newapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newapp.common.PublicHoliday
import com.example.newapp.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel (
    private val mainRepository: MainRepository
): ViewModel(){

    private val _publicHolidays: MutableLiveData<List<PublicHoliday>> = MutableLiveData()
    val publicHoliday : LiveData<List<PublicHoliday>> = _publicHolidays

    fun getHolidays() {
        println("Hello from MainViewModel")
        viewModelScope.launch {
            val publicHolidays = mainRepository.getHolidays()
            //todo set this to livedata for views to observe
            _publicHolidays.value = publicHolidays
        }
    }
}