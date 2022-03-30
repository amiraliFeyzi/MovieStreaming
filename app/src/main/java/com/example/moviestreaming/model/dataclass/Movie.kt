package com.example.moviestreaming.model.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val category_name: String,
    val director: String,
    val genre_name: String,
    val id: Int,
    val link_img: String,
    val name: String,
    val published: String,
    val rank: String,
    val rate_imdb: String,
    val time: String
):Parcelable{
    var isFavorite = false
}