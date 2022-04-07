package com.example.moviestreaming.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviestreaming.components.imagview.ImageLoading
import com.example.moviestreaming.databinding.ActivityIntroBinding
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.model.dataclass.Intro
import com.example.moviestreaming.view.auth.AuthActivity
import com.example.moviestreaming.view.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {
    private lateinit var binding:ActivityIntroBinding

    private val viewModel:IntroViewModel by viewModels()

    @Inject
    lateinit var imageLoading: ImageLoading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
    }

    private fun observeData(){
        viewModel.introLiveData.observe(this){
            setUpUi(it)
        }
    }

    private fun setUpUi(intros:List<Intro>){
        val introAdapter = IntroAdapter(imageLoading)
        introAdapter.setData(intros)
        binding.sliderIntro.adapter = introAdapter
        binding.sliderIndicator.setViewPager2(binding.sliderIntro)
        binding.btnGoApp.setOnClickListener {
            if (UserInformation.email.isNullOrEmpty() && UserInformation.password.isNullOrEmpty()){
                startActivity(Intent(this , AuthActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this , MainActivity::class.java))
                finish()
            }

        }
    }
}