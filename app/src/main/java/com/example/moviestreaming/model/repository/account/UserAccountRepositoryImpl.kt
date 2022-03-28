package com.example.moviestreaming.model.repository.account

import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.dataclass.Subscription
import com.example.moviestreaming.model.datasource.account.local.UserAccountLocalDataSource
import com.example.moviestreaming.model.datasource.account.remote.UserAccountRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserAccountRepositoryImpl (private val userAccountRemoteDataSource: UserAccountRemoteDataSource , private val userAccountLocalDataSource: UserAccountLocalDataSource):UserAccountRepository {
    override suspend fun getBuyAccountInfo(): Flow<List<BuyAccount>> {
        return flow {
            emit(userAccountRemoteDataSource.getBuyAccountInfo())
        }
    }

    override suspend fun getSubscriptionUser(email: String): Flow<List<Subscription>> {
        return flow {
            emit(userAccountRemoteDataSource.getSubscriptionUser(email))
        }
    }

    override fun saveSubscriptionUser(subscription: Long) =
        userAccountLocalDataSource.saveSubscriptionUser(subscription)


    override fun getSubscription(): Long {
        return userAccountLocalDataSource.getSubscriptionUser()
    }

    override suspend fun sendSubscription(email: String, subscription: String) =
        userAccountRemoteDataSource.sendSubscription(email , subscription)
}