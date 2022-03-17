package com.example.moviestreaming.model.network

import com.example.moviestreaming.model.dataclass.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("slider.php")
    suspend fun getSlider():List<Slider>

    @GET("getInformationHomePage.php")
    suspend fun getMovieListHome(@Query("category_name")categoryName:String):List<Movie>

    @GET("getAllInformationHomePage.php")
    suspend fun getAllMovieList(@Query("category_name")categoryName:String):List<Movie>

    @GET("getDetail.php")
    suspend fun getDetailMovie(@Query("id_item")idMovie:String):List<DetailMovie>

    @GET("getSeason.php")
    suspend fun getSeasonsMovie(@Query("id_item")idMovie:String):List<Season>

    @GET("getCast.php")
    suspend fun getCastMovie(@Query("id_item")idMovie: String):List<Cast>

    @GET("getEpisodes.php")
    suspend fun getEpisodeMovie(@Query("id_season")idSeason:String):List<Episode>
}