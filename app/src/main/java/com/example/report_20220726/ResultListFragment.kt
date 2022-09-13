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



    /*
    override fun setArguments(args: Bundle?) {
     testData = args!!.getSerializable("test1") as Player
    }
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = ResultListBinding.inflate(inflater, container, false)
        val binding2 = ResultListItemBinding.inflate(inflater, container, false)

        val adapter = ResultListAdapter() { Player ->
            Toast.makeText(requireContext(), "참가자 ${Player.name} ${binding2.resultListItem2.text}  입니다.", Toast.LENGTH_SHORT).show()
        }

        val bundle = arguments

        val test = bundle?.getSerializable("value1") as ComList
        val test2 = bundle?.getSerializable("value2") as Result

        adapter.datalist = test.playerList
        adapter.datalist2 = test2.playerResultList

        binding.resultListView.adapter = adapter
        binding.resultListView.layoutManager = LinearLayoutManager(requireContext())



        //for(i in 0..)
       //binding.textView.text
        println("번들" + test.playerList[3].name)

        return binding.root

    }
}