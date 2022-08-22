package com.example.report_20220726

import android.util.Log
import kotlin.random.Random

interface Play {
    fun playGame(other: List<Player>): PlayResult

}

sealed class PlayResult {
    object Win : PlayResult()
    object Lose : PlayResult()
    object Draw : PlayResult()
}

class Me(name: String, hand: String) : Player(name, hand)
class Com(name: String, hand: String) : Player(name, hand)


open class Player(val name: String, val hand: String) : Play {


    override fun playGame(otherList: List<Player>): PlayResult {
        //플레이어 수
        val otherNum = otherList.size
        val otherMap = otherList.map { it.name to it.hand }.toMap()
        val otherSet: MutableSet<String> = mutableSetOf()

        for ((key, value) in otherMap) {
            otherSet.add(value)
        }

        //val compareResult = mutableListOf<String>()
        Log.v("테스트", otherMap.get("com1").toString())

        Log.v("테스트2", otherMap.get("com2").toString())
        println(otherSet)

        when (otherNum) {
            2 -> {
                when (otherSet.size) {
                    1 -> return PlayResult.Draw
                    2 -> {

                        when (otherMap.get("나")) {
                            "가위" -> when (otherSet.filterNot { it == "가위" }.get(0)) {
                                "바위" -> return PlayResult.Lose
                                "보" -> return PlayResult.Win
                            }
                            "바위" -> when (otherSet.filterNot { it == "바위" }.get(0)) {
                                "가위" -> return PlayResult.Win
                                "보" -> return PlayResult.Lose
                            }
                            "보" -> when (otherSet.filterNot { it == "보" }.get(0)) {
                                "가위" -> return PlayResult.Lose
                                "바위" -> return PlayResult.Win
                            }

                        }
                    }
                }

            }
            else -> {
                when (otherSet.size) {
                    1, 3 -> return PlayResult.Draw
                    2 -> {

                        when (otherMap.get("나")) {
                            "가위" -> when (otherSet.filterNot { it == "가위" }.get(0)) {
                                "바위" -> return PlayResult.Lose
                                "보" -> return PlayResult.Win
                            }
                            "바위" -> when (otherSet.filterNot { it == "바위" }.get(0)) {
                                "가위" -> return PlayResult.Win
                                "보" -> return PlayResult.Lose
                            }
                            "보" -> when (otherSet.filterNot { it == "보" }.get(0)) {
                                "가위" -> return PlayResult.Lose
                                "바위" -> return PlayResult.Win
                            }

                        }
                    }
                }
            }
        }

        return PlayResult.Win
    }
}

