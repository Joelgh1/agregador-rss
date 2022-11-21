package com.joelgh.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpNavigation()
        //findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.page_2
    }

    override fun onStart() {
        super.onStart()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.page_2
    }

    private fun setUpBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    private fun setUpNavigation(){
        binding?.bottomNavigation?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> findNavController(R.id.nav_host_fragment).navigate(R.id.rssManagementFragment)
                R.id.page_2 -> findNavController(R.id.nav_host_fragment).navigate(R.id.rssFeedFragment)
                R.id.page_3 -> findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
            }
            true
        }
    }
}