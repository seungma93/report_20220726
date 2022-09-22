package com.example.report_20220726

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.report_20220726.databinding.PlayerListBinding
import com.example.report_20220726.databinding.ResultListBinding
import kotlin.random.Random

class PlayerListFragment : Fragment() {
    companion object {
        const val PLAYER_NUMBER_KEY = "PLAYER_NUMBER_KEY"
    }
    private val numOfPlayer get() = requireArguments().getInt(PLAYER_NUMBER_KEY)
    private lateinit var binding : PlayerListBinding
    private val model : MainModelView by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩
        binding = PlayerListBinding.inflate(inflater, container, false)
        // 초기화
        initView(binding)
        return binding.root
    }

    fun initView(binding : PlayerListBinding) {
        // 컴퓨터 리스트 생성
        val comList = ComList(numOfPlayer)
        val playerList = comList.createComList()
        // adapter 객체 생성 클릭시 토스트 구현
        val adapter = PlayerListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} 입니다.", Toast.LENGTH_SHORT).show()
        }
        // datalist에 모든 플레이어 리스트 전달
        adapter.datalist = playerList

        val nameObserver = Observer<Int> { newName ->
            // Update the UI, in this case, a TextView.
            binding.playerNum.text = newName.toString()
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(requireActivity(), nameObserver)


        // 바인딩
        binding.apply {
            // playerListView에 adapter 및 layoutmanager 연결
            playerListView.adapter = adapter
            // 가위 버튼 클릭
            btnScissor.setOnClickListener {
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

            btnNum.setOnClickListener{
                model.currentName.setValue(numOfPlayer)
            }
        }
    }

}