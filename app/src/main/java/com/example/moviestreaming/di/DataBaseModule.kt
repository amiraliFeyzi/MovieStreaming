package com.example.moviestreaming.di

import android.content.Context
import androidx.room.Room
import com.example.moviestreaming.model.database.AppDataBase
import com.example.moviestreaming.model.database.MovieDao
import com.example.moviestreaming.utils.variables.AppDataBaseNAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(context , AppDataBase::class.java, AppDataBaseNAME ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao {
        return appDataBase.movieDao()
    }
}