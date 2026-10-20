package com.maxrave.simpmusic.expect.ui

import androidx.compose.ui.graphics.ImageBitmap

actual fun decodeImageBitmap(bytes: ByteArray): ImageBitmap? = null

actual suspend fun persistPickedImage(
    bytes: ByteArray,
    fileName: String,
): String? = null
