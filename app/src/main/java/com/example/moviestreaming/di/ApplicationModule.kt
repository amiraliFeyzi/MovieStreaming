package com.example.moviestreaming.di

import android.content.Context
import android.content.SharedPreferences
import com.example.moviestreaming.components.imagview.CoilImageLoading
import com.example.moviestreaming.components.imagview.ImageLoading
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