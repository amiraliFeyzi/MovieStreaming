package com.example.moviestreaming.di

import com.example.moviestreaming.model.datasource.movie.MovieRemoteDataSource
import com.example.moviestreaming.model.network.ApiService
import com.example.moviestreaming.model.repository.movie.MovieRepository
import com.example.moviestreaming.model.repository.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(apiService: ApiService):MovieRepository{
        return MovieRepositoryImpl(MovieRemoteDataSource(apiService))
    }

}