package com.example.moviestreaming.model.datasource.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.model.network.ApiService

class MovieRemoteDataSource(private val apiService: ApiService): MovieDataSource {

    override suspend fun getSliderMovie(): List<Slider>  = apiService.getSlider()

    override suspend fun getMovieList(categoryName:String): List<Movie>  =
        apiService.getMovieListHome(categoryName)

    override suspend fun getAllMovieList(categoryName: String): List<Movie> =
        apiService.getAllMovieList(categoryName)

}