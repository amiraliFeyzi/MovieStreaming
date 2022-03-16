package com.example.moviestreaming.model.repository.detailmovie

import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season
import kotlinx.coroutines.flow.Flow

interface DetailMovieRepository {
    suspend fun getDetailMovie(idMovie:String):Flow<List<DetailMovie>>

    suspend fun getSeasonMovie(idMovie: String):Flow<List<Season>>

    suspend fun getCastMovie(idMovie: String):Flow<List<Cast>>

    suspend fun getSimilarMovie(categoryName:String):Flow<List<Movie>>




}