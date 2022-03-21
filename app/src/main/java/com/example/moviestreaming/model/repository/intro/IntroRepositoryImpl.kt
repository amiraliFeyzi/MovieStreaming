package com.example.moviestreaming.model.repository.intro

import com.example.moviestreaming.model.dataclass.Intro
import com.example.moviestreaming.model.datasource.intro.IntroDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IntroRepositoryImpl  (private val introRemoteDataSource: IntroDataSource):IntroRepository {
    override suspend fun getIntro(): Flow<List<Intro>> {
        return flow {
            emit(introRemoteDataSource.getIntro())
        }
    }
}