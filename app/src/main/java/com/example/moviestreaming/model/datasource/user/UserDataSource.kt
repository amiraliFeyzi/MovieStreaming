package com.example.moviestreaming.model.datasource.user

interface UserDataSource {
    suspend fun login(email:String , password:String):String

    suspend fun signUp(email:String ,phone:String ,  password:String):String

    fun saveInformationUser(email:String ,phone:String ,  password:String)

    fun loadToken()

}