package com.bandaging

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.bandaging.screens.NoInternetAnimatedDialog
import com.bandaging.screens.NoInternetScreen
import com.bandaging.screens.WebViewScreen
import com.bandaging.ui.theme.BandagingTheme
import com.bandaging.utils.NetworkObserver
import com.bandaging.utils.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var networkObserver: NetworkObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            runBlocking {
                delay(700)
                false
            }
        }
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(applicationContext)
        setContent {
            BandagingTheme {
                var isWebViewScreen by rememberSaveable { mutableStateOf(false) }
                var isShowDialog by rememberSaveable { mutableStateOf(false) }
                var oneTime by rememberSaveable { mutableStateOf(true) }

                val state = networkObserver.observe()
                    .collectAsState(initial = if (networkObserver.isActuallyConnected()) Status.AVAILABLE else Status.UNAVAILABLE)

                if (oneTime) {
                    isWebViewScreen = state.value == Status.AVAILABLE
                    oneTime = false
                }

                LaunchedEffect(key1 = state.value) {
                    if (state.value != Status.AVAILABLE) isWebViewScreen = false
                }
                if (!isWebViewScreen) NoInternetScreen {
                    if (state.value == Status.AVAILABLE) isWebViewScreen = true else isShowDialog =
                        true
                } else WebViewScreen()

                NoInternetAnimatedDialog(visible = isShowDialog) {
                    isShowDialog = false
                    if (state.value == Status.AVAILABLE) isWebViewScreen = true
                }
            }
        }
    }
}

