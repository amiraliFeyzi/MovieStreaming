package com.example.moviestreaming.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieRepository: MovieRepository):BaseViewModel() {

    private val _searchMovieListLiveData = MutableLiveData<List<Movie>>()

    val searchMovieListLiveData:LiveData<List<Movie>> get() = _searchMovieListLiveData

    fun searchMovie(name:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            movieRepository.searchMovie(name).collect{
                _searchMovieListLiveData.postValue(it)
            }


        }
    }
}