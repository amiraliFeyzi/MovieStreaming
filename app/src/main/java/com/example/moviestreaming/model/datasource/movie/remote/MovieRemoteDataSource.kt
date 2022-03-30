package com.example.moviestreaming.model.datasource.movie.remote

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider

interface MovieRemoteDataSource {
    suspend fun getSliderMovie():List<Slider>

    suspend fun getMovieList(categoryName:String):List<Movie>

    suspend fun getAllMovieList(categoryName:String):List<Movie>

    suspend fun searchMovie(name:String):List<Movie>

}