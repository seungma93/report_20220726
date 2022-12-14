package com.example.report_20220726

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.report_20220726.databinding.PlayerListItemBinding


class PlayerListAdapter(val itemClick: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerListAdapter.MyViewHolder>() {

    var datalist =
        mutableListOf<Player>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    class MyViewHolder(
        private val binding: PlayerListItemBinding,
        val itemClick: (Player) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private var player: Player? = null

        init {
            /*
            binding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    player?.let {
                        itemClick.invoke(it)
                    }
                }
            })

             */
            binding.root.setOnClickListener {
                player?.let {
                    itemClick(it)
                }
            }
        }

        fun bind(player: Player) {
            this.player = player
            binding.playerListItem.text = player.name
        }

    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            PlayerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position])

    }
}