package com.example.report_20220726

import android.util.Log


sealed class playResult{
    object win : playResult(){
        val value = "이겼다"
    }
    object lose : playResult(){
        val value = "졌다"
    }
    object draw : playResult(){
        val value = "비겼다"
    }
}

interface play{
    fun play(other : Com) : playResult
    fun playCase(otherNum : Int, vararg other: Com)


}

sealed class handValue{
    object rock : handValue()
    object paper : handValue()
    object scissors : handValue()
}


class player(val myHand: handValue) : handValue(), play {


    override fun play(other : Com): playResult {

        val com = Com()
        val comHand = com.comResult()

        if (myHand == handValue.rock) {
            return when (comHand) {
                comValue.rock -> playResult.draw
                comValue.scissors -> playResult.win
                comValue.paper -> playResult.lose
            }
        } else if (myHand == handValue.paper) {
            return when (comHand) {
                comValue.rock -> playResult.win
                comValue.scissors -> playResult.lose
                comValue.paper -> playResult.draw
            }
        } else {
            return when (comHand) {
                comValue.rock -> playResult.lose
                comValue.scissors -> playResult.draw
                comValue.paper -> playResult.win

            }
        }

    }



    override fun playCase(otherNum: Int, vararg other: Com)  {

        var array: ArrayList<Com> = arrayListOf<Com>()
        for(i in otherNum){
            array.add(other)
        }



        if (otherNum == 1){
        }else if ( other.size == 2){






        }

    }


}








