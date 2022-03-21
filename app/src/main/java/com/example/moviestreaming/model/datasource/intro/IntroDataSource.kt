package com.example.moviestreaming.model.datasource.intro

import com.example.moviestreaming.model.dataclass.Intro

interface IntroDataSource {
    suspend fun getIntro():List<Intro>
}