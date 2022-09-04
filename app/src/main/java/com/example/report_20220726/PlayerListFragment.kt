package com.example.report_20220726

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlin.random.Random

class PlayerListFragment : Fragment() {
    var value: String? = ""
    lateinit var mainActivity: MainActivity

    fun random(): HandValue {
        // 3가지 랜덤 난수 발생
        var randomNum = Random.nextInt(3)

        return when (randomNum) {
            1 -> HandValue.Scissor
            2 -> HandValue.Rock
            else -> HandValue.Paper
        }
    }



    //fragment에서 mainActivity에 닿는 방법
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) mainActivity = context

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        arguments?.let{
            value = it.getString("value")
        }

            val otherNumber = value!!.toInt()
            // 컴퓨터 플레이어 생성
            val playerList = mutableListOf<Player>()

            for (i: Int in 1..otherNumber) {
                playerList.add(i - 1, Com("com$i", random()))
            }

        val ct = container!!.getContext();
        val playListAdapter = PlayerListAdapter(mainActivity )

            .adapt = dogAdapter
       // println("안녕 $value")

        return inflater.inflate(R.layout.player_list, container, false)

    }
}