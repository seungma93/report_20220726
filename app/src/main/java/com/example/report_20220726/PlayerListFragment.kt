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
    var value: String? = ""
    var mainActivity: MainActivity? = null



    fun random(): HandValue {
        // 3가지 랜덤 난수 발생
        var randomNum = Random.nextInt(3)

        return when (randomNum) {
            1 -> HandValue.Scissor
            2 -> HandValue.Rock
            else -> HandValue.Paper
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            value = it.getString("value")
        }

        val otherNumber = value!!.toInt()
        // 컴퓨터 플레이어 생성
        val playerList = mutableListOf<Player>()
        var me = Player("나", random())
        playerList.add(0,me)

        for (i in 1..otherNumber) {
            playerList.add(i , Com("com$i", random()))
        }

        val binding = PlayerListBinding.inflate(inflater, container, false)
        val adapter = PlayerListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} 입니다.", Toast.LENGTH_SHORT).show()
        }
        adapter.datalist = playerList
        binding.playerListView.adapter = adapter
        binding.playerListView.layoutManager = LinearLayoutManager(requireContext())



        binding.btnScissor.setOnClickListener{

            // 본인 플레이어 생성 (가위)
            val me = Player("나", HandValue.Scissor)
            playerList[0] = me

            //val bundle = Bundle()
            //val test1 = Player("test",HandValue.Scissor)
            //bundle.putSerializable("test1", test1)



            val comList = ComList(playerList)


            val playerResultList = mutableListOf<PlayResult>()

            for(i in 0..playerList.size-1){
                playerResultList.add(playerList[i].playGame(playerList))
            }

            val resultList = Result(playerResultList)

            mainActivity?.setDateAtFragment2(ResultListFragment(),comList, resultList)

        }

        binding.btnRock.setOnClickListener{

            // 본인 플레이어 생성 (가위)
            val me = Player("나", HandValue.Rock)

            when (me.playGame(playerList)) {

                PlayResult.Win -> mainActivity?.setDateAtFragment(ResultListFragment(),"바위")

            }
        }

        binding.btnPaper.setOnClickListener{

            // 본인 플레이어 생성 (가위)
            val me = Player("나", HandValue.Paper)

            when (me.playGame(playerList)) {

                PlayResult.Win -> mainActivity?.setDateAtFragment(ResultListFragment(),"보")

            }
        }





        return binding.root
    }
}