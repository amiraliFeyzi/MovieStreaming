package com.example.moviestreaming.model.network

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("slider.php")
    suspend fun getSlider():List<Slider>

    @GET("getInformationHomePage.php")
    suspend fun getMovieListHome(@Query("category_name")categoryName:String):List<Movie>
}