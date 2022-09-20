package com.example.report_20220726


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.report_20220726.databinding.ActivityMainBinding

sealed class EndPoint{
    data class PlayerListF(val playerNum: Int) : EndPoint()
    data class ResultListF(val comList: ComList, val result: Result): EndPoint()
    object Error : EndPoint()
}

interface RSPGame{
    fun navigateFragment(endPoint: EndPoint)
}

class MainActivity : AppCompatActivity(), RSPGame {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val editCom = binding.comInput

        binding.buttonNext.setOnClickListener {
            val editText = editCom.getText().toString()
            val playerNum = EndPoint.PlayerListF(editText.toInt())

            if (editText.isEmpty()) {
                setFragmnet(ErrorFragment())
            } else {
                navigateFragment(playerNum)
            }
        }
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
                    it.putInt(PlayerListFragment.PLAYER_NUMBER_KEY, endPoint.playerNum)
                    setFragmnet(PlayerListFragment())
                }
                is EndPoint.ResultListF -> {
                   it.putSerializable(ResultListFragment.COMLIST_KEY, endPoint.comList)
                   it.putSerializable(ResultListFragment.RESULT_KEY, endPoint.result)
                   setFragmnet((ResultListFragment()))
                }
                is EndPoint.Error -> {
                }
            }
        }
    }


}

