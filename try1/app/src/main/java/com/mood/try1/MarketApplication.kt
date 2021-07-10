package com.mood.try1

import android.app.Application
import timber.log.Timber

class MarketApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}