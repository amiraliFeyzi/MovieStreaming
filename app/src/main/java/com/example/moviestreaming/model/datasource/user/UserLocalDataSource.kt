package com.example.moviestreaming.model.datasource.user

import android.content.SharedPreferences
import com.example.moviestreaming.model.`object`.UserInformation
import com.example.moviestreaming.utils.variables.EMAIL
import com.example.moviestreaming.utils.variables.PASSWORD
import com.example.moviestreaming.utils.variables.PHONE

class UserLocalDataSource(private val sharedPreferences: SharedPreferences):UserDataSource {
    override suspend fun login(email: String, password: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, phone: String, password: String): String {
        TODO("Not yet implemented")
    }

    override  fun saveInformationUser(email: String, phone: String, password: String) {
       sharedPreferences.edit().apply {
           putString(EMAIL,email)
           putString(PHONE , phone)
           putString(PASSWORD , password)

       }.apply()
    }

    override fun loadToken() {
        sharedPreferences.apply {
            UserInformation.updateData(this.getString(EMAIL ,null) , this.getString(PHONE , null  ) ,this.getString(
                PASSWORD , null) )
        }

    }
}