package com.example.report_20220726

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.report_20220726.databinding.PlayerListBinding
import com.example.report_20220726.databinding.WinBinding

class WinFragment : Fragment() {



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

        arguments?.let {
            value = it.getSerializable("value") as Player
        }
        val binding = WinBinding.inflate(inflater, container, false)




        binding.textView.text =


        return inflater.inflate(R.layout.win, container, false)

    }
}