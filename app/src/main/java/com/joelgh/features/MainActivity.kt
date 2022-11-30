package com.joelgh.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joelgh.rss_aggregator.NavGraphDirections
import com.joelgh.rss_aggregator.R
import com.joelgh.rss_aggregator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBinding()
        setUpNavigation()
    }

    private fun setUpBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    private fun setUpNavigation(){
        binding?.bottomNavigation?.setOnItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> navigateToManagement()
                R.id.page_2 -> navigateToFeed()
                R.id.page_3 -> navigateToProfile()
            }
            true
        }

    }

    override fun onStart() {
        super.onStart()
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.page_2
    }

    private fun navigateToFeed(){
        findNavController(R.id.nav_host_fragment).navigate(NavGraphDirections.actionToRssFeed())
    }

    private fun navigateToManagement(){
        findNavController(R.id.nav_host_fragment).navigate(NavGraphDirections.actionToRssManagement())
    }

    private fun navigateToProfile(){
        findNavController(R.id.nav_host_fragment).navigate(NavGraphDirections.actionToProfile())
    }

}