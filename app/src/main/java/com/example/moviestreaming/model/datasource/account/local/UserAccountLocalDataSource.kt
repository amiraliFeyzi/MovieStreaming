package com.example.moviestreaming.model.datasource.account.local

interface UserAccountLocalDataSource {

    fun saveSubscriptionUser(subscription:Long)

    fun getSubscriptionUser():Long
}