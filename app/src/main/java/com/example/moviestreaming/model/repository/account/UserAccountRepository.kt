package com.example.moviestreaming.model.repository.account

import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.dataclass.Subscription
import kotlinx.coroutines.flow.Flow

interface UserAccountRepository {
    suspend fun getBuyAccountInfo():Flow<List<BuyAccount>>

    suspend fun getSubscriptionUser(email: String):Flow<List<Subscription>>

    fun saveSubscriptionUser(subscription:Long)

    fun getSubscription():Long

    suspend fun sendSubscription(email: String,subscription: String)


}