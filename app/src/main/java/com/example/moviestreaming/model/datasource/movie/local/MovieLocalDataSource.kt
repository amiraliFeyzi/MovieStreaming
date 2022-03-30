package com.example.moviestreaming.model.datasource.movie.local

import com.example.moviestreaming.model.dataclass.MovieEntity

interface MovieLocalDataSource {
    suspend fun addMovieToFavorite(movieEntity: MovieEntity)

    suspend fun getMovieFavorite():List<MovieEntity>

    suspend fun deleteMovieFavorite(movieEntity: MovieEntity)
}