package com.example.moviestreaming.base

import androidx.fragment.app.Fragment
import com.example.moviestreaming.cumponents.imagview.ImageLoading
import javax.inject.Inject

abstract class BaseFragment :Fragment() , View{
    @Inject
    lateinit var imageLoading: ImageLoading
}