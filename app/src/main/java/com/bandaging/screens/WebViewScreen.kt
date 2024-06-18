package com.bandaging.screens

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.bandaging.ui.theme.Red
import com.bandaging.utils.Constants

@SuppressLint("SetJavaScriptEnabled")
@Preview(showSystemUi = true)
@Composable
fun WebViewScreen(url: String = Constants.URL) {
    val loadingPercentage = rememberSaveable { mutableIntStateOf(0) }
    val shouldShowLoading = rememberSaveable { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        view?.loadUrl(url)
                        return true

                    }
                }
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView?, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        loadingPercentage.intValue = newProgress
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }, modifier = Modifier.fillMaxSize(), update = {
            it.loadUrl(url)
        })

        if (loadingPercentage.intValue < 100 && shouldShowLoading.value) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Red)
            }

        } else {
            LaunchedEffect(key1 = true) {
                shouldShowLoading.value = false
            }
        }

    }

}
