package com.example.moviestreaming.model.datasource.episode

import com.example.moviestreaming.model.dataclass.Episode
import com.example.moviestreaming.model.network.ApiService

class EpisodeRemoteDataSource (private val apiService: ApiService):EpisodeDataSource {
    override suspend fun getEpisodeMovie(idSeasons: String): List<Episode> =
        apiService.getEpisodeMovie(idSeasons)
}