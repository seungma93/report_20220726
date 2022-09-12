package com.example.report_20220726

import android.util.Log
import java.io.Serializable
import kotlin.random.Random

interface Play {
    fun playGame(other: MutableList<Player>): PlayResult

}

sealed class PlayResult {
    object Win : PlayResult()
    object Lose : PlayResult()
    object Draw : PlayResult()
}

enum class HandValue {
    Rock,
    Scissor,
    Paper
}


class Com(name: String, hand: HandValue) : Player(name, hand), Serializable
class ComList(val playerList: MutableList<Player>) : Serializable

open class Player(val name: String, val hand: HandValue) : Play, Serializable {


    override fun playGame(otherList: MutableList<Player>): PlayResult {

        // 컴퓨터의 수
        val otherNum = otherList.size
        // 본인 추가
        otherList.add(this)
        for(i:Int in 0..otherList.size-1){
            if(i == otherList.size-1){
                when(otherList[i].hand){
                    HandValue.Rock -> println(otherList[i].name + " = 바위")
                    HandValue.Paper -> println(otherList[i].name + " = 보")
                    HandValue.Scissor -> println(otherList[i].name + " = 가위")
                }
            }else{
                when(otherList[i].hand){
                    HandValue.Rock -> println(otherList[i].name + " = 바위")
                    HandValue.Paper -> println(otherList[i].name + " = 보")
                    HandValue.Scissor -> println(otherList[i].name + " = 가위")
                }

            }
        }


        // 맵에 모두 담음
        val allMap = otherList.map { it.name to it.hand }.toMap()
        // 비교할 set 생성
        val compareSet: MutableSet<HandValue> = mutableSetOf()
        // set에 담음
        for ((key, value) in allMap) {
            compareSet.add(value)
        }

        fun compareHand(me: Player, compareSet: MutableSet<HandValue>) : PlayResult {
            return when (me.hand) {
                HandValue.Scissor -> when (compareSet.filterNot { it == HandValue.Scissor }
                    .get(0)) {
                    HandValue.Rock -> PlayResult.Lose
                    HandValue.Paper -> PlayResult.Win
                    HandValue.Scissor -> PlayResult.Draw
                }
                HandValue.Rock -> when (compareSet.filterNot { it == HandValue.Rock }.get(0)) {
                    HandValue.Scissor -> PlayResult.Win
                    HandValue.Paper -> PlayResult.Lose
                    HandValue.Rock -> PlayResult.Draw
                }
                HandValue.Paper -> when (compareSet.filterNot { it == HandValue.Paper }.get(0)) {
                    HandValue.Scissor -> return PlayResult.Lose
                    HandValue.Rock -> return PlayResult.Win
                    HandValue.Paper -> PlayResult.Draw
                }

            }
        }


        //val compareResult = mutableListOf<String>()
        Log.v("테스트", allMap.get("com1").toString())

        Log.v("테스트2", allMap.get("com2").toString())
        println(compareSet)

        return when (otherNum) {
            1 -> {
                when (compareSet.size) {
                    2 -> compareHand(this, compareSet)
                    else-> PlayResult.Draw
                    }
                }
            else -> {
                when (compareSet.size) {
                    2 -> compareHand(this, compareSet)
                    else -> PlayResult.Draw
                    }
                }
            }

        }


    }


