package com.clone.scoutemove

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Scoutemove:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}