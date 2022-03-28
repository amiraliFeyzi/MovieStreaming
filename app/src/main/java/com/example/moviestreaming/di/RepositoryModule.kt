package com.example.moviestreaming.di

import android.content.SharedPreferences
import com.example.moviestreaming.model.datasource.account.local.UserAccountLocalDataSourceImpl
import com.example.moviestreaming.model.datasource.account.remote.UserAccountRemoteDataSourceImpl
import com.example.moviestreaming.model.datasource.detailmovie.DetailMovieRemoteDataSource
import com.example.moviestreaming.model.datasource.episode.EpisodeRemoteDataSource
import com.example.moviestreaming.model.datasource.genre.GenreRemoteDataSource
import com.example.moviestreaming.model.datasource.intro.IntroRemoteDataSource
import com.example.moviestreaming.model.datasource.movie.MovieRemoteDataSource
import com.example.moviestreaming.model.datasource.user.UserDataSource
import com.example.moviestreaming.model.datasource.user.UserLocalDataSource
import com.example.moviestreaming.model.datasource.user.UserRemoteDataSource
import com.example.moviestreaming.model.network.ApiService
import com.example.moviestreaming.model.repository.account.UserAccountRepository
import com.example.moviestreaming.model.repository.account.UserAccountRepositoryImpl
import com.example.moviestreaming.model.repository.detailmovie.DetailMovieRepository
import com.example.moviestreaming.model.repository.detailmovie.DetailMovieRepositoryImpl
import com.example.moviestreaming.model.repository.episode.EpisodeRepository
import com.example.moviestreaming.model.repository.episode.EpisodeRepositoryImpl
import com.example.moviestreaming.model.repository.genre.GenreRepository
import com.example.moviestreaming.model.repository.genre.GenreRepositoryImpl
import com.example.moviestreaming.model.repository.intro.IntroRepository
import com.example.moviestreaming.model.repository.intro.IntroRepositoryImpl
import com.example.moviestreaming.model.repository.movie.MovieRepository
import com.example.moviestreaming.model.repository.movie.MovieRepositoryImpl
import com.example.moviestreaming.model.repository.user.UserRepository
import com.example.moviestreaming.model.repository.user.UserRepositoryImpl
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

    @Singleton
    @Provides
    fun provideDetailMoveRepository(apiService: ApiService):DetailMovieRepository{
        return DetailMovieRepositoryImpl(DetailMovieRemoteDataSource(apiService))
    }

    @Singleton
    @Provides
    fun provideEpisodeMovieRepository(apiService: ApiService):EpisodeRepository{
        return EpisodeRepositoryImpl(EpisodeRemoteDataSource(apiService))
    }

    @Singleton
    @Provides
    fun provideGenreRepository(apiService: ApiService):GenreRepository{
        return GenreRepositoryImpl(GenreRemoteDataSource(apiService))
    }

    @Singleton
    @Provides
    fun provideIntroRepository(apiService: ApiService):IntroRepository{
        return IntroRepositoryImpl(IntroRemoteDataSource(apiService))
    }

    @Singleton
    @Provides
    fun provideUserRepository(apiService: ApiService , sharedPreferences: SharedPreferences):UserRepository{
        return UserRepositoryImpl(UserRemoteDataSource(apiService) , UserLocalDataSource(sharedPreferences))
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(sharedPreferences: SharedPreferences):UserLocalDataSource{
        return UserLocalDataSource(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideUserAccountRepository(apiService: ApiService ,sharedPreferences: SharedPreferences):UserAccountRepository{
        return UserAccountRepositoryImpl(UserAccountRemoteDataSourceImpl(apiService) , UserAccountLocalDataSourceImpl(sharedPreferences))
    }

}