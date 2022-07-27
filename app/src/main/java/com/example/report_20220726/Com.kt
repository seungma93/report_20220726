package com.example.report_20220726

import kotlin.random.Random

class Com {

    var random_num = Random.nextInt(3)


    fun comResult() : String {
        return when (random_num) {
            0 -> "가위"
            1 -> "바위"
            2 -> "보"
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

}