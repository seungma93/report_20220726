package com.example.report_20220726

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PlayerListAdapter (val context: Context, val playerList: MutableList<Player>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /* LayoutInflater는 item을 Adapter에서 사용할 View로 부풀려주는(inflate) 역할을 한다. */
        val view: View = LayoutInflater.from(context).inflate(R.layout.player_list_item, null)


        val playerName = view.findViewById<TextView>(R.id.playerListItem)


        /* ArrayList<Dog>의 변수 dog의 이미지와 데이터를 ImageView와 TextView에 담는다. */
        val playerList = playerList[position]

        playerName.text = "참가자 : " + playerList.name
        println("잘가 $playerName.text")
        return view
    }

    override fun getItem(position: Int): Any {
        return playerList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return playerList.size
    }
}