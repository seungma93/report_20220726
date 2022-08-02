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




        button_Paper.setOnClickListener{
            val computer = Com()
            val computer2 = Com()
        val player = player(handValue.rock)

            player.playCase(computer, computer2 )



        }

        button_Rock.setOnClickListener{



        }

        button_Scisseor.setOnClickListener{


        }

    }


}

