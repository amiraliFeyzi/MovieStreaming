package com.example.moviestreaming.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviestreaming.model.dataclass.MovieEntity

@Database(entities = [MovieEntity::class] , version = 1 , exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun movieDao():MovieDao
}