package com.example.moviestreaming.model.datasource.movie.local

import com.example.moviestreaming.model.database.MovieDao
import com.example.moviestreaming.model.dataclass.MovieEntity

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) :MovieLocalDataSource {

    override suspend fun addMovieToFavorite(movieEntity: MovieEntity) =
        movieDao.addMovie(movieEntity)

    override suspend fun getMovieFavorite(): List<MovieEntity> =
        movieDao.getMovies()

    override suspend fun deleteMovieFavorite(movieEntity: MovieEntity) =
        movieDao.deleteMovie(movieEntity)
}