package com.example.moviestreaming.model.repository.genre

import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.model.dataclass.Movie
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    suspend fun getGenre():Flow<List<Genre>>

    suspend fun getGenreMovie(genreName:String):Flow<List<Movie>>

}