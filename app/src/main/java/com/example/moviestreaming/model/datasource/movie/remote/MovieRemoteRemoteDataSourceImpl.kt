package com.example.moviestreaming.model.datasource.movie.remote

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.model.network.ApiService

class MovieRemoteRemoteDataSourceImpl(private val apiService: ApiService): MovieRemoteDataSource {

    override suspend fun getSliderMovie(): List<Slider>  = apiService.getSlider()

    override suspend fun getMovieList(categoryName:String): List<Movie>  =
        apiService.getMovieListHome(categoryName)

    override suspend fun getAllMovieList(categoryName: String): List<Movie> =
        apiService.getAllMovieList(categoryName)

    override suspend fun searchMovie(name: String): List<Movie> =
        apiService.searchMovie(name)

}