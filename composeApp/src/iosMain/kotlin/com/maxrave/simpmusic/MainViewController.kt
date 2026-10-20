package com.maxrave.simpmusic

import androidx.compose.ui.window.ComposeUIViewController
import com.maxrave.common.AppIdentity
import com.maxrave.data.di.loader.loadAllModules
import com.maxrave.simpmusic.di.viewModelModule
import com.maxrave.simpmusic.utils.VersionManager
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.simpmusic.lastfm.configLastfm

private var isKoinStarted = false

fun MainViewController() = ComposeUIViewController {
    if (!isKoinStarted) {
        startKoin {
            loadAllModules(
                AppIdentity(
                    applicationId = "com.maxrave.simpmusic.ios",
                    versionName = BuildKonfig.versionName,
                    platform = "iOS",
                    isDevBuild = true,
                ),
            )
            loadKoinModules(viewModelModule)
        }
        configLastfm(BuildKonfig.lastfmApiKey, BuildKonfig.lastfmSecret)
        VersionManager.initialize()
        isKoinStarted = true
    }
    App()
}