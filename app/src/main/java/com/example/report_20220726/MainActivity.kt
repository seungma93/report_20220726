package com.example.report_20220726

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textMe = findViewById<TextView>(R.id.text_me)
        val textCom1 = findViewById<TextView>(R.id.text_com1)
        val textCom2 = findViewById<TextView>(R.id.text_com2)
        val textResult = findViewById<TextView>(R.id.text_result)
        val buttonPaper = findViewById<Button>(R.id.button_Paper)
        val buttonRcok = findViewById<Button>(R.id.button_Rock)
        val buttonScisseor = findViewById<Button>(R.id.button_Scissors)
        val editCom = findViewById<EditText>(R.id.com_input)

        fun random(): String {
            // 3가지 랜덤 난수 발생
            val randomNum = Random.nextInt(3)

            return when (randomNum) {
                0 -> "가위"
                1 -> "바위"
                2 -> "보"
                else -> "오류"
            }
        }

        buttonPaper.setOnClickListener {
            textMe.setText("보")
            // 본인 플레이어 생성 (보)
            val me = Player("나", "보")

            // 컴퓨터 숫자 받아옴
            val otherNumber = editCom.getText().toString().toInt()

            // 컴퓨터 플레이어 생성
            val playerList = mutableListOf<Player>()
            playerList.add(0, me)




            for (i: Int in 1..otherNumber) {
                playerList.add(i, Com("com$i", random()))
            }

            when (otherNumber) {
                1 -> textCom1.setText(playerList[1].hand)
                2 ->{
                    textCom1.setText(playerList[1].hand)
                    textCom2.setText(playerList[2].hand)
                }
            }

            when (me.playGame(playerList)) {
                PlayResult.Win -> textResult.setText("이겼다")
                PlayResult.Lose -> textResult.setText("졌다")
                PlayResult.Draw -> textResult.setText("비겼다")


            }


        }
/*
        buttonScisseor.setOnClickListener {
            textMe.setText("가위")
            // 본인 플레이어 생성 (가위)
            val me = Player("가위")

            // 컴퓨터 숫자 받아옴
            val otherNumber = editCom.getText().toString().toInt()

            // 컴퓨터 플레이어 생성
            val comList = Array<Player>(otherNumber) { i -> Player(random()) }

            if (me.playGame(me, *comList) == PlayResult.Win) {
                textResult.setText("이겼다")
            } else if (me.playGame(me, *comList) == PlayResult.Lose) {
                textResult.setText("졌다")
            } else {
                textResult.setText("비겼다")
            }




        }

        buttonRcok.setOnClickListener {
            textMe.setText("바위")
            // 본인 플레이어 생성 (바위)
            val me = Player("바위")

            // 컴퓨터 숫자 받아옴
            val otherNumber = editCom.getText().toString().toInt()

            // 컴퓨터 플레이어 생성
            var comList = Array<Player>(otherNumber) { i -> Player(random()) }

            if (me.playGame(me, *comList) == PlayResult.Win) {
                textResult.setText("이겼다")
            } else if (me.playGame(me, *comList) == PlayResult.Lose) {
                textResult.setText("졌다")
            } else {
                textResult.setText("비겼다")
            }




        }

 */

    }

}

