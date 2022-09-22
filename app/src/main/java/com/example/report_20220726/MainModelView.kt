package com.example.report_20220726


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainModelView : ViewModel() {

    val currentName: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun test() = currentName
}