package com.example.moviestreaming.model.repository.user

import com.example.moviestreaming.model.dataclass.Subscription
import com.example.moviestreaming.model.datasource.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl (private val userRemoteDataSource:UserDataSource , private val userLocalDataSource: UserDataSource):UserRepository {
    override suspend fun login(email: String, password: String): Flow<String> {
        return flow {
            emit(userRemoteDataSource.login(email , password))
        }
    }

    override suspend fun signUp(email: String, phone: String, password: String): Flow<String> {
        return flow {
            emit(userRemoteDataSource.signUp(email, phone, password))
        }
    }

    override fun saveInformationUser(email: String, phone: String, password: String)  =
        userLocalDataSource.saveInformationUser(email , phone, password)




}