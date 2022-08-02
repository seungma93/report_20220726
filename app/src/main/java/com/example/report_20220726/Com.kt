package com.example.report_20220726

import kotlin.random.Random

class Com   {

    val randomNum = Random.nextInt(3)

    fun comResult() : comValue  {

        if (randomNum == 0) {
            return comValue.rock

        } else if (randomNum == 1) {
            return comValue.paper

        } else {
           return comValue.scissors

        }
    }

    fun Hand() : comValue {
        return this.comResult()
    }
}

sealed class comValue{
    object rock : comValue()
    object paper : comValue()
    object scissors : comValue()
}