package com.example.moviestreaming.model.datasource.account.remote

import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.dataclass.Subscription

interface UserAccountRemoteDataSource {
    suspend fun getBuyAccountInfo():List<BuyAccount>

    suspend fun getSubscriptionUser(email:String):List<Subscription>

    suspend fun sendSubscription(email: String,subscription: String)
}