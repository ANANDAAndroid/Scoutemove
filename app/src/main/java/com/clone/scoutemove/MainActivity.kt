package com.clone.scoutemove

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.clone.scoutemove.screens.NoInternetDialog
import com.clone.scoutemove.screens.NoInternetScreen
import com.clone.scoutemove.screens.WebViewScreen
import com.clone.scoutemove.ui.theme.ScoutemoveTheme
import com.clone.scoutemove.utils.NetworkObserver
import com.clone.scoutemove.utils.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var networkObserver: NetworkObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(applicationContext)
        setContent {
            ScoutemoveTheme {
                var isWebViewScreen by rememberSaveable { mutableStateOf(false) }
                var isShowDialog by rememberSaveable { mutableStateOf(false) }
                val state = networkObserver.observe()
                    .collectAsState(initial = if (networkObserver.isActuallyConnected()) Status.AVAILABLE else Status.UNAVAILABLE)

                LaunchedEffect(key1 = true) {
                    isWebViewScreen = when {
                        state.value == Status.AVAILABLE -> {
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
                LaunchedEffect(key1 = state.value) {
                    if (state.value != Status.AVAILABLE) isWebViewScreen = false
                }
                if (!isWebViewScreen) NoInternetScreen {
                    if (state.value == Status.AVAILABLE) isWebViewScreen = true else isShowDialog =
                        true
                } else WebViewScreen()

                when {
                    isShowDialog -> NoInternetDialog {
                        isShowDialog = false
                        if (state.value == Status.AVAILABLE) isWebViewScreen = true
                    }

                }
            }
        }
    }
}

