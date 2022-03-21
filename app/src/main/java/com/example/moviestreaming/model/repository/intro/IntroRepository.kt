package com.example.moviestreaming.model.repository.intro

import com.example.moviestreaming.model.dataclass.Intro
import kotlinx.coroutines.flow.Flow

interface IntroRepository {
    suspend fun getIntro():Flow<List<Intro>>
}