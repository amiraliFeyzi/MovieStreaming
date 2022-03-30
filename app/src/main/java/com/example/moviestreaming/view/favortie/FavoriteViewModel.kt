package com.example.moviestreaming.view.favortie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.MovieEntity
import com.example.moviestreaming.model.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository):BaseViewModel() {
    private val _movieLiveData = MutableLiveData<List<MovieEntity>>()

    val movieLiveData:LiveData<List<MovieEntity>> get() = _movieLiveData

    init {
        getMovieFavoriteList()
    }
    private fun getMovieFavoriteList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            movieRepository.getMovieFromFavorite().collect{
                _movieLiveData.postValue(it)
            }
        }
    }

    fun removeItemFromFavorite(movieEntity: MovieEntity){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler){
            movieRepository.deleteMovieFromFavorite(movieEntity)
        }
    }
}