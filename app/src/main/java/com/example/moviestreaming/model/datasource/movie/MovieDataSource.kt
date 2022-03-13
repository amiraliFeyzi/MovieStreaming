package com.example.moviestreaming.model.datasource.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider

interface MovieDataSource {
    suspend fun getSliderMovie():List<Slider>

    suspend fun getMovieList(categoryName:String):List<Movie>

    suspend fun getAllMovieList(categoryName:String):List<Movie>

}