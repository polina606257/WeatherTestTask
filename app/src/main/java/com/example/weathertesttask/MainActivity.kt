package com.example.weathertesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> findNavController(R.id.nav_host_fragment).navigate(R.id.fiveDayWeatherFragment)
                R.id.navigation_tab2 -> findNavController(R.id.nav_host_fragment).navigate(R.id.tab2Fragment)
                R.id.navigation_tab3 -> findNavController(R.id.nav_host_fragment).navigate(R.id.tab3Fragment)
                R.id.navigation_tab4 -> findNavController(R.id.nav_host_fragment).navigate(R.id.tab4Fragment)
                R.id.navigation_tab5 -> findNavController(R.id.nav_host_fragment).navigate(R.id.tab5Fragment)
            }
            true
        }
    }
}
