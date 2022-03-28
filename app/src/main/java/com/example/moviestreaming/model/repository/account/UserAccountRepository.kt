package com.example.moviestreaming.model.repository.account

import com.example.moviestreaming.model.dataclass.BuyAccount
import kotlinx.coroutines.flow.Flow

interface UserAccountRepository {
    suspend fun getBuyAccountInfo():Flow<List<BuyAccount>>

}