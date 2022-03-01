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
    val publicHolidays : LiveData<List<PublicHoliday>> = _publicHolidays

    private val _privateHolidays: MutableLiveData<List<PublicHoliday>> = MutableLiveData()
    val privateHolidays: LiveData<List<PublicHoliday>> = _privateHolidays

    fun getPublicHolidays() {
        println("Hello from MainViewModel")
        viewModelScope.launch {
            val publicHolidays = mainRepository.getPublicHolidays()
            _publicHolidays.value = publicHolidays
        }
    }

    fun getPrivateHolidays() {
        viewModelScope.launch {
            val privateHolidays = mainRepository.getPrivateHolidays()
            _privateHolidays.value = privateHolidays
        }
    }

}