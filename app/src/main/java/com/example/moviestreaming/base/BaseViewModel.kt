package com.example.moviestreaming.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

abstract class BaseViewModel :ViewModel(){
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}