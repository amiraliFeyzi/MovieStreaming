package com.example.moviestreaming.base
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity(),BaseView {

    override val rootContext: Context
        get() = this



}