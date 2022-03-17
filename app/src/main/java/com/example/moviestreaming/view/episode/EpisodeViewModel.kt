package com.example.moviestreaming.view.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Episode
import com.example.moviestreaming.model.repository.episode.EpisodeRepository
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor( private val savedStateHandle: SavedStateHandle ,private val episodeRepository: EpisodeRepository):BaseViewModel() {

    private val _episodeLivaData = MutableLiveData<List<Episode>>()

    val episodeLivaData:LiveData<List<Episode>> get() = _episodeLivaData

    private fun getSeasonId():String{
        return savedStateHandle.get<String>(EXTRA_KEY_DATA)!!
    }

    init {
        getEpisodes()
    }

    private fun getEpisodes(){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
            episodeRepository.getEpisode(getSeasonId()).collect{
                _episodeLivaData.postValue(it)
            }
        }
    }


}