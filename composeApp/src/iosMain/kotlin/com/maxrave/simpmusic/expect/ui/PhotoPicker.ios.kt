package com.maxrave.simpmusic.expect.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

@Composable
actual fun photoPickerResult(onResultUri: (String?) -> Unit): PhotoPickerLauncher {
    val latestOnResult = rememberUpdatedState(onResultUri)
    return remember {
        object : PhotoPickerLauncher {
            override fun launch() = latestOnResult.value(null)
        }
    }
}
