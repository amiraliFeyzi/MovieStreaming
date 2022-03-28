package com.example.moviestreaming.model.repository.user

import com.example.moviestreaming.model.dataclass.Subscription
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(email:String , password:String):Flow<String>

    suspend fun signUp(email:String ,phone:String , password:String):Flow<String>

    fun saveInformationUser(email:String ,phone:String ,  password:String)

}