package com.example.moviestreaming.model.dataclass

import androidx.annotation.StringRes

data class Auth(val authSuccessCompleted:Boolean , @StringRes val message:Int = 0 )