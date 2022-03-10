package com.example.moviestreaming.view.home
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseActivity
import com.example.moviestreaming.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigationView()
        setUpNavigationViewWithNavComponent()


    }

    //handle navigationView With NavComponent
    private fun setUpNavigationViewWithNavComponent(){
        val navHostFragment:NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController:NavController = navHostFragment.navController
        binding.navigationView.setupWithNavController(navController)
    }

    //Handle open & close NavigationView
    private fun setNavigationView(){
        binding.drawerLayout.bringToFront()
        binding.drawerLayout.requestLayout()
        binding.navigationView.setCheckedItem(R.id.homeFragment)

        binding.ivMenu.setOnClickListener {
            if (binding.drawerLayout.isDrawerVisible(GravityCompat.END)) {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    //close drawer when click back
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)

        } else {
            super.onBackPressed()
        }
    }


}