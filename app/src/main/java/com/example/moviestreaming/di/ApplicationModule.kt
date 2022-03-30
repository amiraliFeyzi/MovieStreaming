package com.example.moviestreaming.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviestreaming.cumponents.imagview.CoilImageLoading
import com.example.moviestreaming.cumponents.imagview.ImageLoading
import com.example.moviestreaming.model.database.AppDataBase
import com.example.moviestreaming.model.database.MovieDao
import com.example.moviestreaming.utils.variables.AppDataBaseNAME
import com.example.moviestreaming.utils.variables.SHARED_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideImageLoading():ImageLoading{
        return CoilImageLoading()
    }

    @Singleton
    @Provides
    fun provideSharedPerformances(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences(SHARED_NAME , Context.MODE_PRIVATE)
    }


}