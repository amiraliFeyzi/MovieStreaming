package com.example.moviestreaming.view.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviestreaming.base.BaseViewModel
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.dataclass.Payment
import com.example.moviestreaming.model.repository.account.UserAccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountUserViewModel @Inject constructor(private val userAccountRepository: UserAccountRepository):BaseViewModel() {
    private val _buyAccountListLiveData = MutableLiveData<List<BuyAccount>>()
    private val _paymentLiveData = MutableLiveData<Payment>()

    val buyAccountListLiveData:LiveData<List<BuyAccount>> get() = _buyAccountListLiveData
    val paymentLiveData:LiveData<Payment> get() = _paymentLiveData


    fun getListBuyAccount(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userAccountRepository.getBuyAccountInfo().collect{
                _buyAccountListLiveData.postValue(it)
            }
        }
    }

    fun getSubscriptionUserFromServer(){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userAccountRepository.getSubscriptionUser(UserInformation.email!!).collect{
                it.forEach { subscriptionUser ->
                    userAccountRepository.saveSubscriptionUser(subscriptionUser.subscription.toLong())
                    _paymentLiveData.postValue(Payment("" , subscriptionUser.subscription.toLong()))
                    UserInformation.updateSubscription(subscriptionUser.subscription.toLong())
                }
            }

        }

    }

    fun buyAccountByUser(subscription:Long , chargeTime:String){
        val subscriptionNew =  userAccountRepository.getSubscription()+ subscription
        try {
            userAccountRepository.saveSubscriptionUser(subscriptionNew)
        }finally {
            _paymentLiveData.value = Payment(chargeTime , subscriptionNew)
            sendSubscription(subscriptionNew)
            UserInformation.updateSubscription(subscriptionNew)

        }

    }

    private fun sendSubscription(subscription: Long){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            userAccountRepository.sendSubscription(UserInformation.email!! , subscription.toString())
        }
    }



}