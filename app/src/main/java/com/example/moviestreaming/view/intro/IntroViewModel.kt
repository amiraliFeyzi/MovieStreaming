package com.example.moviestreaming.view.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Intro
import com.example.moviestreaming.model.repository.intro.IntroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class IntroViewModel @Inject constructor(private val introRepository: IntroRepository):BaseViewModel() {
    private val _introLiveData = MutableLiveData<List<Intro>>()

    val introLiveData:LiveData<List<Intro>> get() = _introLiveData

    init {
        getIntro()
    }

    private fun getIntro(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            introRepository.getIntro().collect{
                _introLiveData.postValue(it)
            }
        }
    }
}