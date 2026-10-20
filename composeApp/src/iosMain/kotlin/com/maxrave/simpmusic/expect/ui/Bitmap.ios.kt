package com.maxrave.simpmusic.expect.ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import coil3.Image
import coil3.toBitmap
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image as SkiaImage

actual fun ImageBitmap.toByteArray(): ByteArray? =
    SkiaImage.makeFromBitmap(asSkiaBitmap()).encodeToData(EncodedImageFormat.JPEG, 100)?.bytes

actual fun ImageBitmap.toPngByteArray(): ByteArray? =
    SkiaImage.makeFromBitmap(asSkiaBitmap()).encodeToData(EncodedImageFormat.PNG)?.bytes

actual fun Image.toImageBitmap(): ImageBitmap = toBitmap().asComposeImageBitmap()

actual fun ImageBitmap.toReadableBitmap(
    width: Int,
    height: Int,
): ImageBitmap = this
