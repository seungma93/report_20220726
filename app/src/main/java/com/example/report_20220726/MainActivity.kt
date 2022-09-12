package com.example.report_20220726


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    fun setFragmnet(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_view, fragment)
                    .addToBackStack(null)
                    .commit()

    }

    fun setDateAtFragment(fragment: Fragment, value: String) {
        val bundle = Bundle()
        bundle.putString("value", value)

        fragment.arguments = bundle
        setFragmnet(fragment)
    }



    fun setDateAtFragment2(fragment: Fragment, value: ComList) {
        val bundle = Bundle()
        bundle.putSerializable("value", value)
        fragment.arguments = bundle
        setFragmnet(fragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonNext = findViewById<Button>(R.id.button_next)

        val editCom = findViewById<EditText>(R.id.com_input)



        buttonNext.setOnClickListener {

            val transaction = supportFragmentManager.beginTransaction()

            val editText = editCom.getText().toString()

            if (editText.isEmpty()) {
                transaction.replace(R.id.frame_view, ErrorFragment()).commit()
            } else {
                setDateAtFragment(PlayerListFragment(), editText)
            }
        }

    }
}

