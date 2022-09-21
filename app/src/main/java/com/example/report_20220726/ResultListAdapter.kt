package com.example.report_20220726

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.report_20220726.databinding.ResultListItemBinding


class ResultListAdapter(val itemClick: (Player) -> Unit): RecyclerView.Adapter<ResultListAdapter.MyViewHolder>() {

    var datalist = mutableListOf<Player>()//리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    var datalist2 = mutableListOf<PlayResult>()

    class MyViewHolder(private val binding: ResultListItemBinding, val itemClick: (Player) -> Unit): RecyclerView.ViewHolder(binding.root) {
        private var player : Player? = null
        //private var result : PlayResult? = null
        init{
            binding.root.setOnClickListener{
                player?.let{
                    itemClick(it)
                }
            }
        }
        fun bind(player:Player, result: PlayResult){
            this.player = player
            //this.result = result
            binding.resultListPlayer.text = player.name

            when(player.hand){
                HandValue.Scissor -> binding.resultListHandValue.text = "가위"
                HandValue.Rock -> binding.resultListHandValue.text = "바위"
                HandValue.Paper -> binding.resultListHandValue.text = "보"
            }

            when(result){
                is PlayResult.Win -> binding.resultListResult.text = "이겼다"
                is PlayResult.Lose -> binding.resultListResult.text = "졌다"
                is PlayResult.Draw -> binding.resultListResult.text = "비겼다"

            }

        }

    }


    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=ResultListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding, itemClick)
    }

    override fun getItemCount(): Int = datalist.size

    //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(datalist[position], datalist2[position])
    }
}