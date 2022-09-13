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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // result*.xml 바인딩
        val binding = ResultListBinding.inflate(inflater, container, false)
        val binding2 = ResultListItemBinding.inflate(inflater, container, false)
        // 아답터 생성
        val adapter = ResultListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} ${binding2.resultListItem2.text}  입니다.", Toast.LENGTH_SHORT).show()
        }
        // arguments 받는 변수
        val bundle = arguments
        // 전달 받은 객체 받기
        val test = bundle?.getSerializable("value1") as ComList
        val test2 = bundle?.getSerializable("value2") as Result
        // 아답터의 datalist들에 전달받은 객체의 리스트 넣기
        adapter.datalist = test.playerList
        adapter.datalist2 = test2.playerResultList
        // 아답터 및 레이아웃매니저 연결
        binding.resultListView.adapter = adapter
        binding.resultListView.layoutManager = LinearLayoutManager(requireContext())

        //println("번들" + test.playerList[3].name)

        return binding.root

    }
}