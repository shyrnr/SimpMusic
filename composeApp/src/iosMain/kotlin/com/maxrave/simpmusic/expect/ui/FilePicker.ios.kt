package com.maxrave.simpmusic.expect.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

@Composable
actual fun filePickerResult(onResultUri: (String?) -> Unit): FilePickerLauncher {
    val latestOnResult = rememberUpdatedState(onResultUri)
    return remember {
        object : FilePickerLauncher {
            override fun launch() = latestOnResult.value(null)
        }
    }
}

@Composable
actual fun fileSaverResult(
    fileName: String,
    mimeType: String,
    onResultUri: (String?) -> Unit,
): FilePickerLauncher {
    val latestOnResult = rememberUpdatedState(onResultUri)
    return remember(fileName, mimeType) {
        object : FilePickerLauncher {
            override fun launch() = latestOnResult.value(null)
        }
    }
}
