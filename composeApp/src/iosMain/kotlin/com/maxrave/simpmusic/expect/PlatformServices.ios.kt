package com.maxrave.simpmusic.expect

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UIDeviceOrientationLandscapeLeft
import platform.UIKit.UIDeviceOrientationLandscapeRight
import platform.UIKit.UIDeviceOrientationPortrait
import platform.UIKit.UIDeviceOrientationPortraitUpsideDown
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIPasteboard
import platform.UIKit.UIViewController

actual fun copyToClipboard(label: String, text: String) {
    UIPasteboard.generalPasteboard.string = text
}

actual fun getDownloadFolderPath(): String = "${platform.Foundation.NSHomeDirectory()}/Documents/Downloads"

actual fun openUrl(url: String) {
    val nativeUrl = NSURL(string = url) ?: return
    UIApplication.sharedApplication.openURL(nativeUrl)
}

actual fun shareUrl(
    title: String,
    url: String,
) {
    val root = UIApplication.sharedApplication.keyWindow?.rootViewController ?: return
    val activity = UIActivityViewController(
        activityItems = listOf("$title\n$url"),
        applicationActivities = null,
    )
    topPresentedViewController(root).presentViewController(activity, animated = true, completion = null)
}

actual fun currentOrientation(): Orientation =
    when (UIDevice.currentDevice.orientation) {
        UIDeviceOrientationPortrait, UIDeviceOrientationPortraitUpsideDown -> Orientation.PORTRAIT
        UIDeviceOrientationLandscapeLeft, UIDeviceOrientationLandscapeRight -> Orientation.LANDSCAPE
        else -> Orientation.UNSPECIFIED
    }

// Conservatively avoid automatic background downloads until an iOS network-path monitor is added.
actual fun isOnUnmeteredNetwork(): Boolean = false

private tailrec fun topPresentedViewController(controller: UIViewController): UIViewController =
    controller.presentedViewController?.let(::topPresentedViewController) ?: controller
