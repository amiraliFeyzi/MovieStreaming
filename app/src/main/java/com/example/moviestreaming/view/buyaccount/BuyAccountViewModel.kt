package com.example.moviestreaming.view.buyaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.repository.account.UserAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyAccountViewModel @Inject constructor(private val userAccountRepository: UserAccountRepository):BaseViewModel() {
    private val _buyAccountListLiveData = MutableLiveData<List<BuyAccount>>()

    val buyAccountListLiveData:LiveData<List<BuyAccount>> get() = _buyAccountListLiveData

    init {
        getListBuyAccount()
    }

    private fun getListBuyAccount(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userAccountRepository.getBuyAccountInfo().collect{
                _buyAccountListLiveData.postValue(it)
            }
        }
    }
}