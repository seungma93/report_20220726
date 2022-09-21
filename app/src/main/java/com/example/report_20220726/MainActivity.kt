package com.example.report_20220726


import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        println("메인테스트")


        binding.buttonNext.setOnClickListener {
            val editText = binding.comInput.text.toString()
            if (editText.isEmpty()) {
                setFragmnet(ErrorFragment())
            } else {
                val playerNum = EndPoint.PlayerListF(editText.toInt())
                println("보내는 수 ${playerNum.playerNum}")
                navigateFragment(playerNum)
            }
        }
        setContentView(binding.root)
    }

    fun setFragmnet(fragment: Fragment) {
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

