package com.example.report_20220726

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val Paper = object : Hand("paper"){
            override fun fight(a : String) {

                when (a){




                }



            }

        }


    }

}