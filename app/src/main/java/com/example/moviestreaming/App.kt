package com.example.moviestreaming

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.moviestreaming.model.datasource.user.UserLocalDataSource
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App :Application()  {

    @Inject
    lateinit var userLocalDataSource: UserLocalDataSource
    override fun onCreate() {
        super.onCreate()

        //initial Timber
        Timber.plant(Timber.DebugTree())

        userLocalDataSource.loadToken()

    }


}