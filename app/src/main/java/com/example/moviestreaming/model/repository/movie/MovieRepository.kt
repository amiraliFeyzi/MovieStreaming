package com.example.moviestreaming.model.repository.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.MovieEntity
import com.example.moviestreaming.model.dataclass.Slider
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getSliderMovie():Flow<List<Slider>>

    suspend fun getMovieList(categoryName:String):List<Movie>

    suspend fun getAllMovieList(categoryName:String):List<Movie>

    suspend fun searchMovie(name:String):Flow<List<Movie>>

    suspend fun getMovieFromFavorite():Flow<List<MovieEntity>>

    suspend fun addMovieToFavorite(movieEntity: MovieEntity)

    suspend fun deleteMovieFromFavorite(movieEntity: MovieEntity)

}