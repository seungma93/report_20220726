package com.example.report_20220726


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainModelView : ViewModel() {

    private val _currentNum = MutableLiveData<Int>()
    val currentNum: LiveData<Int> = _currentNum

    fun setNum(name: Int) {
        _currentNum.value = name
    }
}