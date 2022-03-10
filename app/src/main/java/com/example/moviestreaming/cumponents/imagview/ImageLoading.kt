package com.example.moviestreaming.cumponents.imagview

import android.widget.ImageView
import com.example.moviestreaming.customview.imageview.MovieImageView

interface ImageLoading {
    fun load(imageView: MovieImageView , uri:String)
}