package com.clone.scoutemove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.clone.scoutemove.screens.NoInternetDialog
import com.clone.scoutemove.screens.NoInternetScreen
import com.clone.scoutemove.screens.WebViewScreen
import com.clone.scoutemove.ui.theme.ScoutemoveTheme
import com.clone.scoutemove.utils.NetworkObserver
import com.clone.scoutemove.utils.Status

class MainActivity : ComponentActivity() {
    private lateinit var networkObserver: NetworkObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(this)
        setContent {
            ScoutemoveTheme {
                val state = networkObserver.observe().collectAsState(initial = Status.AVAILABLE)
                when {
                    state.value == Status.AVAILABLE -> { WebViewScreen() }
                    else -> { NoInternetScreen()
                        NoInternetDialog()
                    }
                }
            }
        }
    }
}

