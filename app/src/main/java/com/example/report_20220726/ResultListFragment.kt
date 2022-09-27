package com.example.report_20220726

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.report_20220726.databinding.PlayerListBinding
import com.example.report_20220726.databinding.ResultListBinding
import com.example.report_20220726.databinding.ResultListItemBinding

class ResultListFragment : Fragment() {
    companion object {
        const val COMLIST_KEY = "COMLIST_KEY"
        const val RESULT_KEY = "RESULT_KEY"
    }

    private lateinit var binding: ResultListBinding
    private val comList get() = requireArguments().getSerializable(COMLIST_KEY) as ComList
    private val resultList get() = requireArguments().getSerializable(RESULT_KEY) as ResultList
    private val model: MainModelView by activityViewModels()
    private lateinit var adapter: ResultListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩
        binding = ResultListBinding.inflate(inflater, container, false)
        // 초기화
        initView()
        return binding.root
    }

    private fun initView() {
        // 아답터 생성
        adapter = ResultListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} 입니다.", Toast.LENGTH_SHORT).show()
        }
        // 아답터의 datalist들에 전달받은 객체의 리스트 넣기
        adapter.comList = comList.playerList
        adapter.ResultList = resultList.resultList
        // 아답터 연결
        binding.resultListView.adapter = adapter
        subscribe()
    }

    private fun subscribe() {
        model.currentNum.observe(requireActivity()) {
            binding.playerNum.text = it.toString()
        }
    }
}