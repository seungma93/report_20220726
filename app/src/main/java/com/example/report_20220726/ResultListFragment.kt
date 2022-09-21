package com.example.report_20220726

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.report_20220726.databinding.PlayerListBinding
import com.example.report_20220726.databinding.ResultListBinding
import com.example.report_20220726.databinding.ResultListItemBinding

class ResultListFragment : Fragment() {
    companion object{
        const val COMLIST_KEY = "COMLIST_KEY"
        const val RESULT_KEY = "RESULT_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // result*.xml 바인딩
        val binding = ResultListBinding.inflate(inflater, container, false)
        //val binding2 = ResultListItemBinding.inflate(inflater, container, false)
        // 아답터 생성
        val adapter = ResultListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} 입니다.", Toast.LENGTH_SHORT).show()
        }
        // arguments 받는 변수
        val bundle = arguments
        // 전달 받은 객체 받기
        val comList = bundle?.getSerializable(COMLIST_KEY) as ComList
        val resultList = bundle?.getSerializable(RESULT_KEY) as ResultList

        println("사이즈" + comList.playerList.size)
        for(i in 0..comList.playerList.size-1){
            println("받았다" + comList.playerList[i].name + resultList.resultList[i])
        }

        // 아답터의 datalist들에 전달받은 객체의 리스트 넣기
        adapter.datalist = comList.playerList
        adapter.datalist2 = resultList.resultList
        binding.apply {
            // 아답터 및 레이아웃매니저 연결
            binding.resultListView.adapter = adapter
            binding.resultListView.layoutManager = LinearLayoutManager(requireContext())
        }
        return binding.root

    }
}