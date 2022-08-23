package com.example.report_20220726

import android.util.Log
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
    rock,
    scissor,
    paper
}

class Me(name: String, hand: String) : Player(name, hand)
class Com(name: String, hand: String) : Player(name, hand)


open class Player(val name: String, val hand: HandValue) : Play {


    override fun playGame(otherList: MutableList<Player>): PlayResult {

        // 컴퓨터의 수
        val otherNum = otherList.size
        // 본인 추가
        otherList.add(this)
        // 맵에 모두 담음
        val allMap = otherList.map { it.name to it.hand }.toMap()
        // 비교할 set 생성
        val compareSet: MutableSet<HandValue> = mutableSetOf()

        // set에 담음
        for ((key, value) in allMap) {
            compareSet.add(value)
        }

        fun compareHand(me: Player, compareSet: MutableSet<HandValue>) : PlayResult{
            return when (me.hand) {
                HandValue.scissor -> when (compareSet.filterNot { it == HandValue.scissor }.get(0)) {
                    HandValue.rock->  PlayResult.Lose
                    HandValue.paper ->  PlayResult.Win
                    HandValue.scissor ->  PlayResult.Draw
                }
                HandValue.rock -> when (compareSet.filterNot { it == HandValue.rock }.get(0)) {
                    HandValue.scissor -> PlayResult.Win
                    HandValue.paper -> PlayResult.Lose
                    HandValue.rock->  PlayResult.Draw
                }
                HandValue.paper -> when (compareSet.filterNot { it == HandValue.paper }.get(0)) {
                    HandValue.scissor -> return PlayResult.Lose
                    HandValue.rock -> return PlayResult.Win
                    HandValue.paper -> PlayResult.Draw
                }

            }


        //val compareResult = mutableListOf<String>()
        Log.v("테스트", allMap.get("com1").toString())

        Log.v("테스트2", allMap.get("com2").toString())
        println(compareSet)

        return when (otherNum) {
            1 -> {
                when (compareSet.size) {
                    1 -> PlayResult.Draw
                    2 -> compareHand(this, compareSet)

                    }
                }
            else -> {
                when (compareSet.size) {
                    1, 3 -> PlayResult.Draw
                    2 -> compareHand(this, compareSet)



                    }
                }
            }
        }


    }
}

