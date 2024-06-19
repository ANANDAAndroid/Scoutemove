package com.clone.scoutemove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.dataStore
import com.clone.scoutemove.screens.App
import com.clone.scoutemove.ui.theme.ScoutemoveTheme
import com.clone.scoutemove.utils.AppSerializer
import com.clone.scoutemove.utils.NetworkObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var networkObserver: NetworkObserver
    private val protoDataStore by dataStore("settings.json", AppSerializer)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ScoutemoveTheme {
                App(networkObserver = networkObserver, dataStore = protoDataStore)
            }
        }
    }
}

