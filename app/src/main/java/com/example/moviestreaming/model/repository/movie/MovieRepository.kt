package com.example.moviestreaming.model.repository.movie

import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getSliderMovie():Flow<List<Slider>>

    suspend fun getMovieList(categoryName:String):Flow<List<Movie>>


}