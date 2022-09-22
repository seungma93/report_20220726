package com.example.report_20220726


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.report_20220726.databinding.ActivityMainBinding
import androidx.lifecycle.Observer as Observer

sealed class EndPoint{
    data class PlayerListF(val playerNum: Int) : EndPoint()
    data class ResultListF(val comList: ComList, val resultList: ResultList): EndPoint()
    object Error : EndPoint()
}

interface RSPGame{
    fun navigateFragment(endPoint: EndPoint)
}


class MainActivity : AppCompatActivity(), RSPGame {
    private val binding get() = ActivityMainBinding.inflate(layoutInflater)
    private val model : MainModelView by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribe()

        binding.apply {
            buttonNext.setOnClickListener {
                val editText = comInput.text.toString()
                if (editText.isEmpty()) {
                    setFragmnet(ErrorFragment())
                }else {
                    val playerNum = EndPoint.PlayerListF(editText.toInt())
                    navigateFragment(playerNum)
                }
            }
        btnCheck.setOnClickListener{
            subscribe()
        }

            setContentView(root)
        }
    }
    private fun subscribe() {
        val nameObserver = Observer<Int> { newName ->
            // Update the UI, in this case, a TextView.
            binding.playerNum.text = newName.toString()
        }
        // observe the ViewModel's elapsed time
        model.test().observe(this, nameObserver)
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

