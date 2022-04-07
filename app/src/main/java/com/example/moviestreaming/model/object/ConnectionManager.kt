package com.example.moviestreaming.model.`object`

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectionManager {

    fun checkConnection(context: Context): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val networkInfo: Array<NetworkInfo> = connectivityManager.getAllNetworkInfo()
            if (networkInfo != null) {
                for (i in networkInfo.indices) {
                    if (networkInfo[i].getState() === NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }
}