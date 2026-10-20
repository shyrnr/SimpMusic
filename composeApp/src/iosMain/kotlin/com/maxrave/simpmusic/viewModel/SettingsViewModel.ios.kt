package com.maxrave.simpmusic.viewModel

import com.eygraber.uri.Uri
import com.maxrave.domain.repository.CacheRepository
import com.maxrave.domain.repository.CommonRepository
import platform.Foundation.NSBundle
import platform.Foundation.NSHomeDirectory

actual suspend fun calculateDataFraction(cacheRepository: CacheRepository): SettingsStorageSectionFraction? = null

actual suspend fun restoreNative(
    commonRepository: CommonRepository,
    uri: Uri,
    getData: () -> Unit,
) {
    error("Backup restore is not available in the iOS port yet.")
}

actual suspend fun readPickedFile(uri: Uri): ByteArray {
    error("File picking is not available in the iOS port yet.")
}

actual suspend fun backupNative(
    commonRepository: CommonRepository,
    uri: Uri,
    backupDownloaded: Boolean,
) {
    error("Backup creation is not available in the iOS port yet.")
}

actual fun getPackageName(): String = NSBundle.mainBundle.bundleIdentifier.orEmpty()

actual fun getFileDir(): String = "${NSHomeDirectory()}/Documents"

actual fun changeLanguageNative(code: String) {
    // iOS language selection is managed by the app's Compose resource configuration.
}
