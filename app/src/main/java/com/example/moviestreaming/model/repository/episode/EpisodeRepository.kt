package com.example.moviestreaming.model.repository.episode

import com.example.moviestreaming.model.dataclass.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun getEpisode(idSeason:String):Flow<List<Episode>>
}