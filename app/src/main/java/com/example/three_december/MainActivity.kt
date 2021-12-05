package com.example.three_december

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MyTag"

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationMenu = findViewById(R.id.bottom_navigation_menu)

        bottomNavigationMenu.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.fragment_1 -> {
                    fragment = FerstFragment()


                }
                R.id.fragment_2 -> {
                    fragment = SecondFragment()

                }
            }
            replaceFragment(fragment!!)

            true

        }

        bottomNavigationMenu.selectedItemId =
            savedInstanceState?.getInt(LAYOUT_INFLATER_SERVICE) ?: R.id.fragment_conteiner
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAYOUT_INFLATER_SERVICE, bottomNavigationMenu.selectedItemId)

        val currentFragment = supportFragmentManager.fragments.last()
        supportFragmentManager.putFragment(
            outState,
            currentFragment.javaClass.name,
            currentFragment
        )

    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_conteiner, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}
