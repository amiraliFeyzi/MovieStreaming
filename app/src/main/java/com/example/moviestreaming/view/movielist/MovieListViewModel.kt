package com.example.moviestreaming.view.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Movie
import com.example.moviestreaming.model.repository.movie.MovieRepository
import com.example.moviestreaming.utils.variables.EXTRA_KEY_CATEGORY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle , private val movieRepository: MovieRepository):BaseViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()

    val movieList:LiveData<List<Movie>> get() = _movieList

    private fun getCategoryName(): String {
        return savedStateHandle.get(EXTRA_KEY_CATEGORY)!!
    }

     fun getMovieList(){
         viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
             movieRepository.getAllMovieList(getCategoryName()).collect{
                 _movieList.postValue(it)
             }
         }
     }


}