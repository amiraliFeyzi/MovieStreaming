package com.example.moviestreaming.model.datasource.account.remote

import com.example.moviestreaming.model.dataclass.BuyAccount
import com.example.moviestreaming.model.network.ApiService

class UserAccountRemoteDataSourceImpl(private val apiService: ApiService):UserAccountRemoteDataSource {
    override suspend fun getBuyAccountInfo(): List<BuyAccount> =
        apiService.getBuyAccountInfo()
}