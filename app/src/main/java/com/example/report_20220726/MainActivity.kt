package com.example.report_20220726

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val text_me = findViewById<TextView>(R.id.text_me)
        val text_com = findViewById<TextView>(R.id.text_com)
        val text_result = findViewById<TextView>(R.id.text_result)
        val button_Paper = findViewById<Button>(R.id.button_Paper)
        val button_Rock = findViewById<Button>(R.id.button_Rock)
        val button_Scisseor = findViewById<Button>(R.id.button_Scissors)

        var paper = Paper("보")
        var rock = Rock("바위")
        var scissors = Scissors("가위")


                button_Paper.setOnClickListener{

                    var com = Com()

                    var com_choice = com.comResult()

                    var result = paper.fight(com.comResult())



                    text_me.text = "나 ${paper.paper}"
                    text_com.text = "컴 ${com_choice}"
                    text_result.text = "결과 :${result}"

        }

        button_Rock.setOnClickListener{

            var com = Com()

            var com_choice = com.comResult()

            var result = rock.fight(com.comResult())



            text_me.text = "나 ${rock.rock}"
            text_com.text = "컴 ${com_choice}"
            text_result.text = "결과 :${result}"

        }

        button_Scisseor.setOnClickListener{

            var com = Com()

            var com_choice = com.comResult()

            var result = scissors.fight(com.comResult())



            text_me.text = "나 ${scissors.scissors}"
            text_com.text = "컴 ${com_choice}"
            text_result.text = "결과 :${result}"

        }

    }


}

