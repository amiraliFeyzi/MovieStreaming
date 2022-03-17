package com.example.moviestreaming.view.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayMovieViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle):BaseViewModel() {
    private val _linkMovieLiveData = MutableLiveData<String>()
    val linkMovieLiveData:LiveData<String> get() = _linkMovieLiveData

    private fun getLinkMovie(){
        val link = savedStateHandle.get<String>(EXTRA_KEY_DATA)
        _linkMovieLiveData.value = link!!
    }

    init {
        getLinkMovie()
    }
}