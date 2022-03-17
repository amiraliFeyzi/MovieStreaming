package com.example.moviestreaming.model.repository.episode

import com.example.moviestreaming.model.dataclass.Episode
import com.example.moviestreaming.model.datasource.episode.EpisodeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EpisodeRepositoryImpl (private val episodeRemoteDataSource: EpisodeDataSource):EpisodeRepository {
    override suspend fun getEpisode(idSeason: String): Flow<List<Episode>> {
        return flow {
            emit(episodeRemoteDataSource.getEpisodeMovie(idSeason))
        }
    }
}