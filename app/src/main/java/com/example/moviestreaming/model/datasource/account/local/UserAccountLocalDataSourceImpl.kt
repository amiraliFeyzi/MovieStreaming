package com.example.moviestreaming.model.datasource.account.local

import android.content.SharedPreferences
import com.example.moviestreaming.utils.variables.SUBSCRIPTION

class UserAccountLocalDataSourceImpl(private val sharedPreferences: SharedPreferences):UserAccountLocalDataSource {

    override  fun saveSubscriptionUser(subscription: Long) {
        sharedPreferences.edit().putLong(SUBSCRIPTION , subscription).apply()
    }

    override fun getSubscriptionUser(): Long {
        return sharedPreferences.getLong(SUBSCRIPTION , System.currentTimeMillis())
    }
}