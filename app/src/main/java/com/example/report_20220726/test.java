package com.example.report_20220726;

public class test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Hello World!");//Hello World! 를 출력한다.
    }


}




sealed class playResult {
    object win : playResult() {
        val value = "이겼다"
    }

    object lose : playResult() {
        val value = "졌다"
    }

    object draw : playResult() {
        val value = "비겼다"
    }
}


class player(val myHand: handValue) : handValue(), play {


        override fun play(other: Com): playResult {

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


        override fun playCase(otherNum: Int, vararg other: Com): playResult {


        if (otherNum == 1) {
        val result = play(other[0])
        return result
        } else if (other.size == 2) {
        if (other[0].Hand() != other[1].Hand() && other[0].Hand() != myHand && other[1].Hand() != myHand) {
        return playResult.draw
        } else if (other[0].Hand() == other[1].Hand() && other[0].Hand() == myHand && other[1].Hand() == myHand) {
        return playResult.draw
        } else {
        if (play(other[0]) == playResult.lose) {
        return playResult.lose
        } else if (play(other[0]) == playResult.win) {
        return playResult.win
        } else {
        if (play(other[1]) == playResult.lose) {
        return playResult.lose
        } else {
        return playResult.win
        }

        }

        }

        } else {
        return playResult.draw
        }

        }