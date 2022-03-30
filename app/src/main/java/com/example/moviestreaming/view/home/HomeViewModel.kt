package com.example.moviestreaming.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.dataclass.Slider
import com.example.moviestreaming.model.repository.movie.MovieRepository
import com.example.moviestreaming.utils.variables.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository):BaseViewModel() {
    private val _sliderLiveData =MutableLiveData<List<Slider>>()
    private val _topMovieImdbLiveData = MutableLiveData<List<Movie>>()
    private val _newMovieLiveData = MutableLiveData<List<Movie>>()
    private val _seriesLiveData = MutableLiveData<List<Movie>>()
    private val _popularMovieLiveData = MutableLiveData<List<Movie>>()
    private val _animationLiveData = MutableLiveData<List<Movie>>()

    val sliderLiveData:LiveData<List<Slider>> get() = _sliderLiveData
    val topMovieImdbLiveData:LiveData<List<Movie>> get() = _topMovieImdbLiveData
    val newMovieLiveData:LiveData<List<Movie>> get() = _newMovieLiveData
    val seriesLiveData:LiveData<List<Movie>> get() = _seriesLiveData
    val popularMovieLiveData:LiveData<List<Movie>> get() = _popularMovieLiveData
    val animationLiveData:LiveData<List<Movie>> get() = _animationLiveData


    fun getSlider(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            movieRepository.getSliderMovie().collect{
                _sliderLiveData.postValue(it)
            }
        }
    }

    fun getTopMovieImdbList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _topMovieImdbLiveData.postValue(movieRepository.getMovieList(CATEGORY_NAME_TOP_MOVIE_IMDB))
        }
    }

    fun getNewMovieList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _newMovieLiveData.postValue(movieRepository.getMovieList(CATEGORY_NAME_MOVIE_NEW))
        }
    }

    fun getSeriesList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _seriesLiveData.postValue(movieRepository.getMovieList(CATEGORY_NAME_SERIES))
        }
    }

    fun getPopularMovieList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _popularMovieLiveData.postValue(movieRepository.getMovieList(CATEGORY_NAME_POPULAR_MOVIE))
        }
    }

    fun getAnimationList(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _animationLiveData.postValue(movieRepository.getMovieList(CATEGORY_NAME_ANIMATION))
        }
    }



}