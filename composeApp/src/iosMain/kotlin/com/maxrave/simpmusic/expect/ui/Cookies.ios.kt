package com.maxrave.simpmusic.expect.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

actual fun createWebViewCookieManager(): WebViewCookieManager =
    object : WebViewCookieManager {
        override fun getCookie(url: String): String = ""
        override fun removeAllCookies() = Unit
    }

@Composable
actual fun PlatformWebView(
    state: MutableState<WebViewState>,
    initUrl: String,
    aboveContent: @Composable (BoxScope.() -> Unit),
    clearCookies: Boolean,
    onPageFinished: (String) -> Unit,
) {
    LaunchedEffect(initUrl) { state.value = WebViewState.Finished }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Embedded sign-in is not available in this iOS port yet.")
        aboveContent()
    }
}

@Composable
actual fun DiscordWebView(
    state: MutableState<WebViewState>,
    aboveContent: @Composable (BoxScope.() -> Unit),
    onLoginDone: (token: String) -> Unit,
) {
    LaunchedEffect(Unit) { state.value = WebViewState.Finished }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Discord sign-in is not available in this iOS port yet.")
        aboveContent()
    }
}
