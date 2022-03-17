package com.example.moviestreaming.model.datasource.episode

import com.example.moviestreaming.model.dataclass.Episode

interface EpisodeDataSource {
    suspend fun getEpisodeMovie(idSeasons:String):List<Episode>
}