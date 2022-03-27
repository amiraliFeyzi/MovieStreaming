package com.example.moviestreaming.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.R
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.Auth
import com.example.moviestreaming.model.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository):BaseViewModel() {

    private val _loginLiveData = MutableLiveData<Auth>()
    private val _signUpLiveData = MutableLiveData<Auth>()

    val loginLiveData:LiveData<Auth> get() = _loginLiveData
    val signUpLiveData:LiveData<Auth> get() = _signUpLiveData

    fun login(email:String , password:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try{
                userRepository.login(email , password).collect{
                    when(it){
                        "Email Not Validate"-> _loginLiveData.postValue(Auth(false , R.string.emailValidate))
                        "Login Ok" -> _loginLiveData.postValue(Auth(true))
                        "Login Failed" -> _loginLiveData.postValue(Auth(false , R.string.loginFailed))
                        "Email Not Exists" -> _loginLiveData.postValue(Auth(false ,  R.string.emailNotExits))

                    }
                }
            }finally {
                userRepository.saveInformationUser(email , "" , password)
            }


        }
    }

    fun signUP(email:String , phone:String , password: String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                userRepository.signUp(email, phone, password).collect{
                    when(it){
                        "Email Not Validate"-> _signUpLiveData.postValue(Auth(false , R.string.emailValidate))
                        "Password More 7 , Less 15" -> _signUpLiveData.postValue(Auth(false , R.string.passwordValue))
                        "Register Ok" -> _signUpLiveData.postValue(Auth(true))
                        "Email Exists"-> _signUpLiveData.postValue(Auth(false ,  R.string.emailExits))
                        "Register Failed" -> _signUpLiveData.postValue(Auth(false , R.string.registerFailed))

                    }
                }
            }finally {
                userRepository.saveInformationUser(email , phone, password)
            }

        }
    }
}