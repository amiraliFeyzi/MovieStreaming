package com.example.moviestreaming.model.datasource.detailmovie

import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season

interface DetailMovieDataSource {
    suspend fun getDetailMovie(idMovie:String):List<DetailMovie>

    suspend fun getSeasonMovie(idMovie: String):List<Season>

    suspend fun getCastMovie(idMovie: String):List<Cast>

    suspend fun getSimilarMovie(categoryName:String):List<Movie>
}