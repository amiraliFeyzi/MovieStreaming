package com.example.moviestreaming

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App :Application()  {

    override fun onCreate() {
        super.onCreate()

        //initial Timber
        Timber.plant(Timber.DebugTree())

    }


}