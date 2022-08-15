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

class Me(name : String , hand: String) : Player(name , hand)
class Com(name : String , hand: String) : Player(name , hand)


open class Player(val name : String, val hand : String ) : Play {



    override fun playGame(otherList: List<Player>): PlayResult {
        //플레이어 수
        val otherNum = otherList.size
        val otherMap = otherList.map { it.name to it.hand }.toMap()
        val otherSet: MutableSet<String> = mutableSetOf()

        for( (key,value) in otherMap){
            otherSet.add(value)
        }

        //val compareResult = mutableListOf<String>()
        Log.v("테스트", otherMap.get("com1").toString())

        Log.v("테스트2", otherMap.get("com2").toString())
        println(otherSet)

        when (otherNum){
            2 -> {
                when(otherSet.size){
                    1 -> return PlayResult.Draw
                    2 -> {
                       // val compareResult = otherSet.filterNot { it == "가위" }
                        when(otherMap.get("나")){
                            "가위" -> when{
                                    otherSet.filter{ it  "바위" }

                            }

                        }
                    }
                }

            }
        }

/*
        if (playerNum == 2 {
            if (set.size == 1) {
                return PlayResult.Draw
            } else {
                if (me.value == "가위") {
                    val list = set.filterNot { it == "가위" }
                    if (list[0] == "바위") {
                        return PlayResult.Lose
                    } else {
                        return PlayResult.Win
                    }
                } else if (me.value == "바위") {
                    val list = set.filterNot { it == "바위" }
                    if (list[0] == "가위") {
                        return PlayResult.Win
                    } else {
                        return PlayResult.Lose
                    }
                } else {
                    val list = set.filterNot { it == "보" }
                    if (list[0] == "바위") {
                        return PlayResult.Win
                    } else {
                        return PlayResult.Lose
                    }
                }

            }

        } else if (playerNum == 2) {
            if (set.size == 1 || set.size == 3) {
                return PlayResult.Draw
            } else {
                if (me.value == "가위") {
                    val list = set.filterNot { it == "가위" }
                    if (list[0] == "바위") {
                        return PlayResult.Lose
                    } else {
                        return PlayResult.Win
                    }

                } else if (me.value == "바위") {
                    val list = set.filterNot { it == "바위" }
                    if (list[0] == "가위") {
                        return PlayResult.Win
                    } else {
                        return PlayResult.Lose
                    }

                } else {
                    val list = set.filterNot { it == "보" }
                    if (list[0] == "가위") {
                        return PlayResult.Lose
                    } else {
                        return PlayResult.Win
                    }
                }
            }
        }

        return PlayResult.Win



    }

 */
        return PlayResult.Win
    }
}

