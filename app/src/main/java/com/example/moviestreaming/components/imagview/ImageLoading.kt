package com.example.moviestreaming.components.imagview

import com.example.moviestreaming.customview.imageview.MovieImageView

interface ImageLoading {
    fun load(imageView: MovieImageView , uri:String)

    fun loadWithCorners(imageView: MovieImageView , uri: String  , corner:Float)

    fun loadCircle(imageView: MovieImageView , uri: String)

}