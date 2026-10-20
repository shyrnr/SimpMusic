package com.maxrave.simpmusic.expect.ui

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.ColorScheme

@Composable
actual fun platformDynamicColorScheme(isDark: Boolean): ColorScheme? = null

actual fun isWallpaperDynamicColorSupported(): Boolean = false

@Composable
actual fun SystemBarAppearanceEffect(isDark: Boolean) = Unit

actual fun isLyricsBlurSupported(): Boolean = false

@Composable
actual fun PlatformCastButton(modifier: Modifier, tint: Color) = Unit

actual fun isPlatformCastAvailable(): Boolean = false

@Composable
actual fun rememberCastReceivers(discover: Boolean): CastReceivers =
    CastReceivers(emptyList(), connect = {}, disconnect = {})

@Composable
actual fun rememberDeviceVolumeController(): DeviceVolumeController? = null

@Composable
actual fun openEqResult(audioSessionId: Int): OpenEqLauncher =
    remember { object : OpenEqLauncher { override fun launch() = Unit } }

@Composable
actual fun rememberLocalNetworkPermission(): LocalNetworkPermissionRequester =
    remember {
        object : LocalNetworkPermissionRequester {
            override fun requestIfNeeded(url: String) = Unit
        }
    }

@Composable
actual fun rememberSaveImagePermission(onResult: (granted: Boolean) -> Unit): SaveImagePermissionRequester {
    val latestOnResult = rememberUpdatedState(onResult)
    return remember {
        object : SaveImagePermissionRequester {
            override fun requestIfNeeded() = latestOnResult.value(false)
        }
    }
}

@Composable
internal actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) = Unit

@Composable
actual fun LoginSyncDialog(onDismiss: () -> Unit) = Unit

@Composable
actual fun HorizontalScrollBar(
    modifier: Modifier,
    scrollState: LazyListState,
    flingBehavior: FlingBehavior?,
) = Unit

@Composable
actual fun HorizontalScrollBar(
    modifier: Modifier,
    scrollState: LazyGridState,
    flingBehavior: FlingBehavior?,
) = Unit
