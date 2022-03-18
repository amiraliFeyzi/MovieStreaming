package com.example.moviestreaming.view.genremovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.repository.genre.GenreRepository
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreMovieViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle,private val genreRepository: GenreRepository):BaseViewModel() {

    private val _genreMovieLiveData = MutableLiveData<List<Movie>>()

    val genreMovieLiveData:LiveData<List<Movie>> get() = _genreMovieLiveData

    private fun getGenreName():String{
        return savedStateHandle.get<String>(EXTRA_KEY_DATA)!!
    }

    init {
        getGenreMovie()
    }

    private fun getGenreMovie(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            genreRepository.getGenreMovie(getGenreName()).collect{
                _genreMovieLiveData.postValue(it)
            }
        }
    }

}