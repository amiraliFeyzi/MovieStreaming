package com.example.moviestreaming.components.imagview

import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.moviestreaming.customview.imageview.MovieImageView

class CoilImageLoading: ImageLoading {
    override fun load(imageView: MovieImageView, uri: String) {
        imageView.load(uri)
    }

    override fun loadWithCorners(imageView: MovieImageView, uri: String, corner: Float) {
        imageView.load(uri){
            transformations(RoundedCornersTransformation(corner))
        }
    }

    override fun loadCircle(imageView: MovieImageView, uri: String) {
        imageView.load(uri){
            transformations(CircleCropTransformation())
        }
    }
}