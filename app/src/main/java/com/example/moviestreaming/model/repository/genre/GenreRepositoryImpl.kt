package com.example.moviestreaming.model.repository.genre

import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.datasource.genre.GenreDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenreRepositoryImpl (private val genreDataSource: GenreDataSource):GenreRepository {
    override suspend fun getGenre(): Flow<List<Genre>> {
        return flow {
            emit(genreDataSource.getGenre())
        }
    }

    override suspend fun getGenreMovie(genreName: String): Flow<List<Movie>> {
        return flow {
            emit(genreDataSource.getGenreMovie(genreName))
        }
    }
}