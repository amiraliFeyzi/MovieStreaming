package com.example.moviestreaming.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviestreaming.model.dataclass.MovieEntity
import androidx.room.Delete

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM favorites")
    suspend fun getMovies():List<MovieEntity>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}