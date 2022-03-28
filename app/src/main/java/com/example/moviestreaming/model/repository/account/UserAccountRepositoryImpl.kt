package com.example.moviestreaming.model.repository.account

import com.example.moviestreaming.model.dataclass.BuyAccount
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
}