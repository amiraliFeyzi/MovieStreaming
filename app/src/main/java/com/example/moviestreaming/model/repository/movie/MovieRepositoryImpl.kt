package com.example.moviestreaming.model.repository.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.MovieEntity
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.model.datasource.movie.local.MovieLocalDataSource
import com.example.moviestreaming.model.datasource.movie.remote.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteRemoteDataSource: MovieRemoteDataSource , private val movieLocalDataSource: MovieLocalDataSource):MovieRepository {

    override suspend fun getSliderMovie(): Flow<List<Slider>> {
       return flow {
           emit(movieRemoteRemoteDataSource.getSliderMovie())
       }
    }

    override suspend fun getMovieList(categoryName: String): List<Movie> {
        val movieServe = movieRemoteRemoteDataSource.getMovieList(categoryName)
        val movieFavorite = movieLocalDataSource.getMovieFavorite()

        val favoriteIdMovie = movieFavorite.map {
            it.id
        }

        movieServe.forEach { movieServe->
            if (favoriteIdMovie.contains(movieServe.id)){
                movieServe.isFavorite = true
            }
        }

        return movieServe
    }

    override suspend fun getAllMovieList(categoryName: String): List<Movie> {
        val movieServe = movieRemoteRemoteDataSource.getAllMovieList(categoryName)
        val movieFavorite = movieLocalDataSource.getMovieFavorite()

        val favoriteIdMovie = movieFavorite.map {
            it.id
        }

        movieServe.forEach { movieServe->
            if (favoriteIdMovie.contains(movieServe.id)){
                movieServe.isFavorite = true
            }
        }

        return movieServe
    }

    override suspend fun searchMovie(name: String): Flow<List<Movie>> {
        return flow {
            emit(movieRemoteRemoteDataSource.searchMovie(name))
        }
    }

    override suspend fun getMovieFromFavorite(): Flow<List<MovieEntity>> {
        return flow {
            emit(movieLocalDataSource.getMovieFavorite())
        }
    }
    override suspend fun addMovieToFavorite(movieEntity: MovieEntity) =
        movieLocalDataSource.addMovieToFavorite(movieEntity)

    override suspend fun deleteMovieFromFavorite(movieEntity: MovieEntity) =
        movieLocalDataSource.deleteMovieFavorite(movieEntity)
}