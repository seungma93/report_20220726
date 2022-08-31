package com.example.report_20220726


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textMe = findViewById<TextView>(R.id.text_me)
        val buttonPaper = findViewById<Button>(R.id.button_Paper)
        val buttonRock = findViewById<Button>(R.id.button_Rock)
        val buttonScisseor = findViewById<Button>(R.id.button_Scissors)
        val editCom = findViewById<EditText>(R.id.com_input)


        fun random(): HandValue {
            // 3가지 랜덤 난수 발생
            var randomNum = Random.nextInt(3)

            return when (randomNum) {
                1 -> HandValue.Scissor
                2 -> HandValue.Rock
                else -> HandValue.Paper
            }
        }

        buttonPaper.setOnClickListener {
            textMe.setText("보")
            // 본인 플레이어 생성 (보)
            val me = Player("나", HandValue.Paper)
            val transaction = supportFragmentManager.beginTransaction()
            val editText = editCom.getText().toString()

            if (editText.isEmpty()) {
                transaction.replace(R.id.frame_view, ErrorFragment()).commit()
            } else {

                // 컴퓨터 숫자 받아옴
                val otherNumber = editText.toInt()


                // 컴퓨터 플레이어 생성
                val playerList = mutableListOf<Player>()

                for (i: Int in 1..otherNumber) {
                    playerList.add(i - 1, Com("com$i", random()))
                }

                when (me.playGame(playerList)) {

                    PlayResult.Win -> transaction.replace(R.id.frame_view, WinFragment()).commit()
                    PlayResult.Lose -> transaction.replace(R.id.frame_view, LoseFragment()).commit()
                    PlayResult.Draw -> transaction.replace(R.id.frame_view, DrawFragment()).commit()

                }

            }
        }

        buttonScisseor.setOnClickListener {
            textMe.setText("가위")
            // 본인 플레이어 생성 (가위)
            val me = Player("나", HandValue.Scissor)
            val transaction = supportFragmentManager.beginTransaction()
            val editText = editCom.getText().toString()


            if (editText.isEmpty()) {
                transaction.replace(R.id.frame_view, ErrorFragment()).commit()
            } else {
                // 컴퓨터 숫자 받아옴
                val otherNumber = editText.toInt()

                // 컴퓨터 플레이어 생성
                val playerList = mutableListOf<Player>()

                for (i: Int in 1..otherNumber) {
                    playerList.add(i - 1, Com("com$i", random()))
                }

                when (me.playGame(playerList)) {

                    PlayResult.Win -> transaction.replace(R.id.frame_view, WinFragment()).commit()
                    PlayResult.Lose -> transaction.replace(R.id.frame_view, LoseFragment()).commit()
                    PlayResult.Draw -> transaction.replace(R.id.frame_view, DrawFragment()).commit()

                }
            }

        }

        buttonRock.setOnClickListener {
            textMe.setText("바위")
            // 본인 플레이어 생성 (바위)
            val me = Player("나", HandValue.Paper)
            val transaction = supportFragmentManager.beginTransaction()
            val editText = editCom.getText().toString()


            if (editText.isEmpty()) {
                transaction.replace(R.id.frame_view, ErrorFragment()).commit()
            } else {

                // 컴퓨터 숫자 받아옴
                val otherNumber = editText.toInt()
                // 컴퓨터 플레이어 생성
                val playerList = mutableListOf<Player>()

                for (i: Int in 1..otherNumber) {
                    playerList.add(i - 1, Com("com$i", random()))
                }

                when (me.playGame(playerList)) {

                    PlayResult.Win -> transaction.replace(R.id.frame_view, WinFragment()).commit()
                    PlayResult.Lose -> transaction.replace(R.id.frame_view, LoseFragment()).commit()
                    PlayResult.Draw -> transaction.replace(R.id.frame_view, DrawFragment()).commit()

                }

            }


        }

    }
}

