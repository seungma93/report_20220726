package com.example.report_20220726


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.report_20220726.databinding.ActivityMainBinding

sealed class EndPoint{
    data class PlayerListF(val playerNum: Int) : EndPoint()
    data class ResultListF(val comList: ComList, val resultList: ResultList): EndPoint()
    object Error : EndPoint()
}

interface RSPGame{
    fun navigateFragment(endPoint: EndPoint)
}

class MainActivity : AppCompatActivity(), RSPGame {
    private lateinit var binding : ActivityMainBinding
    private val model : MainModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
        binding.apply {
            btnNext.setOnClickListener {
                val editText = comInput.text.toString()
                if (editText.isEmpty()) {
                    setFragmnet(ErrorFragment())
                }else {
                    val playerNum = EndPoint.PlayerListF(editText.toInt())
                    navigateFragment(playerNum)
                }
            }
        }
    }

    private fun subscribe() {
        model.currentNum.observe(this) {
            binding.playerNum.text = it.toString()
        }
    }

    private fun setFragmnet(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun navigateFragment(endPoint: EndPoint)  = with(Bundle()){
        this.let {
            when (endPoint) {
                is EndPoint.PlayerListF -> {
                    val fragment = PlayerListFragment()
                    it.putInt(PlayerListFragment.PLAYER_NUMBER_KEY, endPoint.playerNum)
                    fragment.arguments = it
                    setFragmnet(fragment)
                }
                is EndPoint.ResultListF -> {
                   val fragment = ResultListFragment()
                   it.putSerializable(ResultListFragment.COMLIST_KEY, endPoint.comList)
                   it.putSerializable(ResultListFragment.RESULT_KEY, endPoint.resultList)
                   fragment.arguments = it
                   setFragmnet(fragment)
                }
                is EndPoint.Error -> {
                }
            }
        }
    }
}

