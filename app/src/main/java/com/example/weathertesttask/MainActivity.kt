package com.example.weathertesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weathertesttask.ui.HomePage.FiveDayWeatherFragment
import com.example.weathertesttask.ui.Tab2Fragment
import com.example.weathertesttask.ui.Tab3Fragment
import com.example.weathertesttask.ui.Tab4Fragment
import com.example.weathertesttask.ui.Tab5Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> replaceFragment(FiveDayWeatherFragment())
                R.id.navigation_tab2 -> replaceFragment(Tab2Fragment())
                R.id.navigation_tab3 -> replaceFragment(Tab3Fragment())
                R.id.navigation_tab4 -> replaceFragment(Tab4Fragment())
                R.id.navigation_tab5 -> replaceFragment(Tab5Fragment())
            }
            true
        }

        replaceFragment(FiveDayWeatherFragment())

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container, fragment)
            .commit()
    }
}
