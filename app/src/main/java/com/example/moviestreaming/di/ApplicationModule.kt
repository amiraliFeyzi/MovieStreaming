package com.example.moviestreaming.di

import com.example.moviestreaming.cumponents.imagview.CoilImageLoading
import com.example.moviestreaming.cumponents.imagview.ImageLoading
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}