package com.example.report_20220726

interface play {
    fun playGame(me: Player, vararg player: Player): PlayResult
}

sealed class PlayResult {
    object Win : PlayResult()
    object Lose : PlayResult()
    object Draw : PlayResult()
}

class Player(value: String) : Hand(value), play {


    override fun playGame(me: Player, vararg player: Player): PlayResult {
        //플레이어 수
        val playerNum = player.size
        var map: MutableMap<Player, String> = mutableMapOf(me to me.value)
        var set: MutableSet<String> = mutableSetOf()

        for (i: Int in 0 until playerNum) {
            map.put(player[i], player[i].value)
        }
        for ((key, value) in map) {
            set = mutableSetOf(value)
        }

        if (playerNum == 2) {
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

        } else if (playerNum == 3) {
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

}

