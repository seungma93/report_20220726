package com.example.report_20220726

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.report_20220726.databinding.PlayerListBinding
import kotlin.random.Random

class PlayerListFragment : Fragment() {
    companion object {
        const val PLAYER_NUMBER_KEY = "PLAYER_NUMBER_KEY"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 넘겨준 아규먼트 받아옴
        val bundle = arguments
        val value = bundle?.getInt("PLAYER_NUMBER_KEY")
        println("받는쪽 $value")
        // 입력받은 컴퓨터 수
        val inputComNumber = value!!
        // 컴퓨터 리스트 생성
        val comList = ComList(inputComNumber)
        val playerList = comList.createComList()
        // play_list.xml 바인딩
        val binding = PlayerListBinding.inflate(inflater, container, false)
        // adapter 객체 생성 클릭시 토스트 구현
        val adapter = PlayerListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} 입니다.", Toast.LENGTH_SHORT).show()
        }
        // datalist에 모든 플레이어 리스트 전달
        adapter.datalist = playerList
        // 바인딩
        binding.apply{
            // playerListView에 adapter 및 layoutmanager 연결
            playerListView.adapter = adapter
            playerListView.layoutManager = LinearLayoutManager(requireContext())
            // 가위 버튼 클릭
            btnScissor.setOnClickListener{
                // 본인 플레이어 손모양 가위로 변경
                val me = Player("나", HandValue.Scissor)
                comList.playerList[0] = me
                // 프래그먼트 이동시 전달할 Result 객체 생성
                val resultList = ResultList()
                resultList.createResultList(comList)
                // 결과 프래그먼트에 값 전달 및 화면 이동
                val endPoint = EndPoint.ResultListF(comList, resultList)
                // 프래그먼트 호출
                (requireActivity() as? RSPGame)?.navigateFragment(endPoint)
            }
        }
        return binding.root
    }
}