package com.example.moviestreaming.model.datasource.genre

import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.model.dataclass.Movie

interface GenreDataSource {

    suspend fun getGenre():List<Genre>

    suspend fun getGenreMovie(genreName:String):List<Movie>
}