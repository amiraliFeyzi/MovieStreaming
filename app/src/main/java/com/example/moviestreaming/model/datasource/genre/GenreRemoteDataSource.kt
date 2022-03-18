package com.example.moviestreaming.model.datasource.genre

import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.network.ApiService

class GenreRemoteDataSource (private val apiService: ApiService):GenreDataSource {
    override suspend fun getGenre(): List<Genre> =
        apiService.getGenre()

    override suspend fun getGenreMovie(genreName: String): List<Movie> =
        apiService.getGenreMovie(genreName)
}