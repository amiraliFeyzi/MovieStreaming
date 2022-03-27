package com.example.moviestreaming.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment :Fragment() , BaseView{

    override val rootContext: Context
        get() = requireContext()
}