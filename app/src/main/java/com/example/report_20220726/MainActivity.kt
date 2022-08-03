package com.example.report_20220726

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textMe = findViewById<TextView>(R.id.text_me)
        val textCom = findViewById<TextView>(R.id.text_com)
        val textResult = findViewById<TextView>(R.id.text_result)
        val buttonPaper = findViewById<Button>(R.id.button_Paper)
        val buttonRcok = findViewById<Button>(R.id.button_Rock)
        val buttonScisseor = findViewById<Button>(R.id.button_Scissors)
        val editCom = findViewById<EditText>(R.id.com_input)



        buttonPaper.setOnClickListener {
            val player = player(handValue.paper)

            val otherNumber = editCom.getText().toString().toInt()


            var data = Array<Com>(otherNumber) {i -> Com()}



            var result = player.playCase( otherNumber, *data )


            textMe.setText("보")
            var list = Array<String>(otherNumber) { i -> String()}
            for (i in 0..otherNumber - 1){
                if (data[i].comResult() == comValue.rock ){
                    list[i] = "바위"

                }else if (data[i].comResult() == comValue.paper){
                    list[i] = "보"
                }else {
                    list[i] = "가위"
                }
            }
            textCom.setText(list.joinToString(limit = otherNumber) )






            if (result == playResult.win){
                textResult.setText("이겼다")
            }else if (result == playResult.draw){
                textResult.setText("비겼다")
            }else {
                textResult.setText("졌다")
            }



        }

        buttonScisseor.setOnClickListener {
            val player = player(handValue.scissors)

            val otherNumber = editCom.getText().toString().toInt()


            var data = Array<Com>(otherNumber) {i -> Com()}



            var result = player.playCase( otherNumber, *data )


            textMe.setText("가위")
            var list = Array<String>(otherNumber) { i -> String()}
            for (i in 0..otherNumber - 1){
                if (data[i].comResult() == comValue.rock ){
                    list[i] = "바위"

                }else if (data[i].comResult() == comValue.paper){
                    list[i] = "보"
                }else {
                    list[i] = "가위"
                }
            }
            textCom.setText(list.joinToString(limit = otherNumber) )






            if (result == playResult.win){
                textResult.setText("이겼다")
            }else if (result == playResult.draw){
                textResult.setText("비겼다")
            }else {
                textResult.setText("졌다")
            }



        }

        buttonRcok.setOnClickListener {
            val player = player(handValue.rock)

            val otherNumber = editCom.getText().toString().toInt()


            var data = Array<Com>(otherNumber) {i -> Com()}



            var result = player.playCase( otherNumber, *data )


            textMe.setText("바위")
            var list = Array<String>(otherNumber) { i -> String()}
            for (i in 0..otherNumber - 1){
                if (data[i].comResult() == comValue.rock ){
                    list[i] = "바위"

                }else if (data[i].comResult() == comValue.paper){
                    list[i] = "보"
                }else {
                    list[i] = "가위"
                }
            }
            textCom.setText(list.joinToString(limit = otherNumber) )






            if (result == playResult.win){
                textResult.setText("이겼다")
            }else if (result == playResult.draw){
                textResult.setText("비겼다")
            }else {
                textResult.setText("졌다")
            }



        }

    }

}

