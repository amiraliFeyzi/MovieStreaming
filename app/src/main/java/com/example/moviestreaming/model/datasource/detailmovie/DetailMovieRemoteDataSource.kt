package com.example.moviestreaming.model.datasource.detailmovie

import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season
import com.example.moviestreaming.model.network.ApiService

class DetailMovieRemoteDataSource (private val apiService: ApiService):DetailMovieDataSource {
    override suspend fun getDetailMovie(idMovie: String): List<DetailMovie> =
        apiService.getDetailMovie(idMovie)

    override suspend fun getSeasonMovie(idMovie: String): List<Season> =
        apiService.getSeasonsMovie(idMovie)

    override suspend fun getCastMovie(idMovie: String): List<Cast> =
        apiService.getCastMovie(idMovie)

    override suspend fun getSimilarMovie(categoryName: String): List<Movie> =
        apiService.getAllMovieList(categoryName)

}