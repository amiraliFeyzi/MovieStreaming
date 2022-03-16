package com.example.moviestreaming.model.repository.detailmovie

import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season
import com.example.moviestreaming.model.datasource.detailmovie.DetailMovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailMovieRepositoryImpl (private val detailMovieRemoteDataSource: DetailMovieDataSource):DetailMovieRepository {
    override suspend fun getDetailMovie(idMovie: String): Flow<List<DetailMovie>> {
        return flow {
            emit(detailMovieRemoteDataSource.getDetailMovie(idMovie))
        }
    }

    override suspend fun getSeasonMovie(idMovie: String): Flow<List<Season>> {
        return flow {
            emit(detailMovieRemoteDataSource.getSeasonMovie(idMovie))
        }
    }

    override suspend fun getCastMovie(idMovie: String): Flow<List<Cast>> {
        return flow {
            emit(detailMovieRemoteDataSource.getCastMovie(idMovie))
        }
    }

    override suspend fun getSimilarMovie(categoryName: String): Flow<List<Movie>> {
        return flow {
            emit(detailMovieRemoteDataSource.getSimilarMovie(categoryName))
        }
    }
}