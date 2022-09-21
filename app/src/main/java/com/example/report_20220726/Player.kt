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
class ComList(val comNum: Int) : Serializable{
    val playerList = mutableListOf<Player>()

    fun random(): HandValue {
        // 3가지 랜덤 난수 발생
        var randomNum = Random.nextInt(3)
        return when (randomNum) {
            1 -> HandValue.Scissor
            2 -> HandValue.Rock
            else -> HandValue.Paper
        }
    }
    fun createComList() : MutableList<Player> {
        // 임의의 나 객체 생성해서 0번 배열에 추가
        playerList.add(0,Player("나", random()))
        // 입력받은 컴퓨터 수 만큼 컴퓨터 객체 랜덤 생성후 리스트에 추가
        for (i in 1..comNum) {
            playerList.add(i , Com("com$i", random()))
        }
        return playerList
    }
}

class ResultList() : Serializable {
    // 결과 넣을 리스트 생성
    val resultList = mutableListOf<PlayResult>()

    fun createResultList(comList: ComList): MutableList<PlayResult>  {
        // 결과 리스트에 결과 넣기
        println("사이즈" + comList.playerList.size)
        comList.apply{
        for(i in 0..playerList.size-1)
        {
            resultList.add(playerList[i].playGame(playerList))
        }
    }
        return  resultList
}

}
open class Player(val name: String, val hand: HandValue) : Play, Serializable {

    override fun playGame(playerList: MutableList<Player>): PlayResult {

        val playerNum = playerList.size

        // 맵에 모두 담음
        val playerMap = playerList.map { it.name to it.hand }.toMap()
        // 비교할 set 생성
        val compareSet: MutableSet<HandValue> = mutableSetOf()
        // set에 담음
        for ((key, value) in playerMap) {
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
                    HandValue.Scissor -> PlayResult.Lose
                    HandValue.Rock -> PlayResult.Win
                    HandValue.Paper -> PlayResult.Draw
                }

            }
        }
        return when (playerNum) {
            2 -> {
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


