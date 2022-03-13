package com.example.moviestreaming.model.repository.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.model.datasource.movie.MovieDataSource
import com.example.moviestreaming.model.datasource.movie.MovieRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl (private val movieRemoteDataSource: MovieDataSource):MovieRepository {

    override suspend fun getSliderMovie(): Flow<List<Slider>> {
       return flow {
           emit(movieRemoteDataSource.getSliderMovie())
       }
    }

    override suspend fun getMovieList(categoryName: String): Flow<List<Movie>> {
        return flow {
            emit(movieRemoteDataSource.getMovieList(categoryName))
        }
    }

    override suspend fun getAllMovieList(categoryName: String): Flow<List<Movie>> {
        return flow {
            emit(movieRemoteDataSource.getAllMovieList(categoryName))
        }
    }
}