package com.clone.scoutemove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.clone.scoutemove.screens.App
import com.clone.scoutemove.screens.NoInternetAnimatedDialog
import com.clone.scoutemove.screens.NoInternetScreen
import com.clone.scoutemove.screens.WebViewScreen
import com.clone.scoutemove.ui.theme.ScoutemoveTheme
import com.clone.scoutemove.utils.NetworkObserver
import com.clone.scoutemove.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var networkObserver:NetworkObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ScoutemoveTheme {
                App(networkObserver = networkObserver)
            }
        }
    }
}

