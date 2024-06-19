package com.clone.scoutemove.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.clone.scoutemove.utils.Status

@Composable
fun MainScreen( state: Status) {

    var isWebViewScreen by rememberSaveable { mutableStateOf(false) }
    var isShowDialog by rememberSaveable { mutableStateOf(false) }
    var oneTime by rememberSaveable { mutableStateOf(true) }

    if (oneTime) {
        isWebViewScreen = state == Status.AVAILABLE
        oneTime = false
    }

    LaunchedEffect(key1 = state) {
        if (state != Status.AVAILABLE) isWebViewScreen = false
    }
    if (!isWebViewScreen) NoInternetScreen {
        if (state == Status.AVAILABLE) isWebViewScreen = true else isShowDialog =
            true
    } else WebViewScreen()

    NoInternetAnimatedDialog(visible = isShowDialog) {
        isShowDialog = false
        if (state == Status.AVAILABLE) isWebViewScreen = true
    }
}