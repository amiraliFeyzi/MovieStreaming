package com.example.moviestreaming.model.datasource.intro

import com.example.moviestreaming.model.dataclass.Intro
import com.example.moviestreaming.model.network.ApiService

class IntroRemoteDataSource (private val apiService: ApiService):IntroDataSource {
    override suspend fun getIntro(): List<Intro> =
        apiService.getIntro()

}