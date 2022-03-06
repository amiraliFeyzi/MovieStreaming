package com.example.moviestreaming.model.network

import com.example.moviestreaming.model.dataclass.Slider
import retrofit2.http.GET

interface ApiService {
    @GET("slider.php")
    suspend fun getSlider():List<Slider>
}