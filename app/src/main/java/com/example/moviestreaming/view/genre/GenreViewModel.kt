package com.example.moviestreaming.view.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Genre
import com.example.moviestreaming.model.repository.genre.GenreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val genreRepository: GenreRepository):BaseViewModel(){
    private val _genreListLiveData = MutableLiveData<List<Genre>>()

    val genreListLiveData:LiveData<List<Genre>> get() = _genreListLiveData

    init {
        getGenreList()
    }
    private fun getGenreList(){
        viewModelScope.launch (Dispatchers.IO + coroutineExceptionHandler){
            genreRepository.getGenre().collect{
                _genreListLiveData.postValue(it)
            }
        }
    }
}