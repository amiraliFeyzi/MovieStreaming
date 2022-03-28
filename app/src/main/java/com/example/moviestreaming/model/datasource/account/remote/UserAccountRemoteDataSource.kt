package com.example.moviestreaming.model.datasource.account.remote

import com.example.moviestreaming.model.dataclass.BuyAccount

interface UserAccountRemoteDataSource {
    suspend fun getBuyAccountInfo():List<BuyAccount>
}