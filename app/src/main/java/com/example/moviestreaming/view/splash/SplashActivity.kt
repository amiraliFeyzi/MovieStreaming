package com.example.moviestreaming.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseActivity
import com.example.moviestreaming.databinding.ActivitySplashBinding
import com.example.moviestreaming.model.`object`.ConnectionManager
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.view.home.MainActivity
import com.example.moviestreaming.view.intro.IntroActivity

class SplashActivity : BaseActivity() {
    private lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectionManager()
    }


    private fun connectionManager(){
        val handler = Handler()
        if(ConnectionManager.checkConnection(this)){
            handler.postDelayed({
                Toast.makeText(this@SplashActivity , getString(R.string.connected) , Toast.LENGTH_SHORT).show()
                if (!UserInformation.email.isNullOrEmpty() || !UserInformation.password.isNullOrEmpty()){
                    startActivity(Intent(this , MainActivity::class.java))
                    finish()


                }else{
                    startActivity(Intent(this , IntroActivity::class.java))
                    finish()
                }

            }, 4000)
        }else{
            handler.postDelayed({
                showDialog()
            },4000)
        }
    }

    private fun showDialog(){
        val view = showDialogs(R.layout.dialog_check_network)
        view.findViewById<View>(R.id.fab_retry).setOnClickListener {
            view.visibility = View.INVISIBLE
            connectionManager()
        }
    }
}