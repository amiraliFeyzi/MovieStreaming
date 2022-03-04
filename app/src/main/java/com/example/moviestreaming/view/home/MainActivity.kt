package com.example.moviestreaming.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseActivity
import com.example.moviestreaming.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigationViewWithNavComponent()

    }

    //handle navigationView With NavComponent
    private fun setUpNavigationViewWithNavComponent(){
        val navHostFragment:NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController:NavController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment , R.id.searchFragment , R.id.buyAccountFragment , R.id.genreFragment , R.id.favoriteFragment , R.id.favoriteFragment) , binding.drawerLayout)
        binding.navigationView.setupWithNavController(navController)
    }


}