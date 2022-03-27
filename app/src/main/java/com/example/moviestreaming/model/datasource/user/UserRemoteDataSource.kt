package com.example.moviestreaming.model.datasource.user

import com.example.moviestreaming.model.network.ApiService

class UserRemoteDataSource (private val apiService: ApiService):
    UserDataSource {

    override suspend fun login(email: String, password: String): String =
        apiService.login(email , password)

    override suspend fun signUp(email: String, phone: String, password: String): String =
        apiService.signUp(email , phone, password)

    override fun loadToken() {
        TODO("Not yet implemented")
    }

    override  fun saveInformationUser(email: String, phone: String, password: String) {
        TODO("Not yet implemented")
    }
}