package com.example.three_december

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MyTag"

class MainActivity : AppCompatActivity() {

    lateinit var switchFragmentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switchFragmentButton = findViewById(R.id.switch_fragments_button)


        val startFragment = StartFragment()
        val endFragment = EndFragment()

        switchFragmentButton.setOnClickListener {
            val fragment =
                when (supportFragmentManager.findFragmentById(R.id.fragment_conteiner)) {
                    is StartFragment -> endFragment
                    is EndFragment -> startFragment
                    else -> startFragment
                }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_conteiner, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }
    }
}