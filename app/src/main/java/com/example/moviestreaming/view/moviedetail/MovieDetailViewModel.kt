package com.example.moviestreaming.view.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Cast
import com.example.moviestreaming.model.dataclass.DetailMovie
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Season
import com.example.moviestreaming.model.repository.detailmovie.DetailMovieRepository
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val detailMovieRepository: DetailMovieRepository):BaseViewModel() {

    private val _movieLiveData = MutableLiveData<Movie>()
    private val _detailMovieLiveData = MutableLiveData<List<DetailMovie>>()
    private val _seasonMovieLiveData = MutableLiveData<List<Season>>()
    private val _castMovieLiveData = MutableLiveData<List<Cast>>()
    private val _similarMovieLiveData = MutableLiveData<List<Movie>>()

    val movieLiveData:LiveData<Movie> get() = _movieLiveData
    val detailMovieLiveData:LiveData<List<DetailMovie>> get() = _detailMovieLiveData
    val seasonMovieLiveData:LiveData<List<Season>> get() = _seasonMovieLiveData
    val castMovieLiveData:LiveData<List<Cast>> get() = _castMovieLiveData
    val similarMovieLiveData:LiveData<List<Movie>>get() = _similarMovieLiveData


    private fun getMovie():Movie{
        val movie = savedStateHandle.get<Movie>(EXTRA_KEY_DATA)
        _movieLiveData.postValue(movie!!)
        return movie
    }


    fun getDetailMovie(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            detailMovieRepository.getDetailMovie(getMovie().id.toString()).collect{
                _detailMovieLiveData.postValue(it)
            }
        }
    }

    fun getSeasonMovie(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            detailMovieRepository.getSeasonMovie(getMovie().id.toString()).collect{
                _seasonMovieLiveData.postValue(it)
            }
        }
    }

    fun getCastMovie(){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
            detailMovieRepository.getCastMovie(getMovie().id.toString()).collect{
                _castMovieLiveData.postValue(it)
            }
        }
    }

    fun getSimilarMovie(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            detailMovieRepository.getSimilarMovie(getMovie().category_name).collect{
                _similarMovieLiveData.postValue(it)
            }
        }
    }


}