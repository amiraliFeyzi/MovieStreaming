package com.example.moviestreaming.cumponents.imagview

import android.widget.ImageView
import coil.load
import com.example.moviestreaming.customview.imageview.MovieImageView

class CoilImageLoading: ImageLoading {
    override fun load(imageView: MovieImageView, uri: String) {
        imageView.load(uri)
    }
}