# iOS Port Architecture Note

Date: 2026-10-20

## Direction

Use the existing `composeApp` shared UI as the iOS UI, hosted by a small SwiftUI app. Export a static `ComposeApp` framework for `iosArm64` and `iosSimulatorArm64`; connect it to Xcode with Kotlin's `embedAndSignAppleFrameworkForXcode` Gradle task. Keep platform-only APIs behind existing Kotlin `expect`/`actual` seams.

The app is a music player, so its minimal native playback backend uses AVFoundation (`AVPlayer`/`AVPlayerItem`) and configures `AVAudioSession` for the playback category. Background playback also requires the `audio` value in `UIBackgroundModes`; this is declared in the iOS host's Info.plist. Keep signing identity and Team ID user-owned in Xcode settings.

## Current implementation status

- Enabled `iosArm64` and `iosSimulatorArm64` static framework targets in `composeApp/build.gradle.kts`.
- Added a SwiftUI/Xcode host under `iosApp/` and wired it to `MainViewController()`.
- Added iOS `actual` declarations corresponding to the 52 common `expect` declarations. This is source-level coverage, not feature parity or compile proof: several platform functions remain limited or unsupported. iOS HMAC and Spotify TOTP now use CommonCrypto and have focused iOS vector tests, but those tests have not been run.
- Replaced the iOS media-handler placeholder for active `iosArm64` and `iosSimulatorArm64` targets with a minimal AVPlayer-backed handler that resolves selected items through `StreamRepository`, manages queue/current-item state, and supports core transport controls. Added focused queue and video-ID tests; neither tests nor Kotlin/Native compilation has run yet.
- Added a Kotlin/Native-compatible Ktor Brotli codec dependency for iOS and removed duplicate target-specific TODO implementations. This enables Brotli request/response content coding in source, but has not been compiled or exercised on Apple targets.
- iOS login WebViews, richer radio/related-track behavior, persisted playback restore, casting, and several platform integrations remain incomplete or limited.
- Added `.github/workflows/ios-simulator.yml` to compile the device framework, run simulator tests, build an unsigned simulator app, and upload the `.app` artifact. No workflow run has been triggered.
- The host Info.plist declares background audio, local-network and photo-library usage text, and `CADisableMinimumFrameDurationOnPhone` to opt into higher display refresh rates where supported.
- Latest local checks: `git diff --check` passed in the root and `core` submodule, and `Info.plist` parsed successfully. No Gradle/Kotlin/Native/Xcode build or iOS test was run here; `.github/workflows/ios-simulator.yml` is prepared to perform those checks on macOS.

## Official references

- Kotlin Multiplatform direct Xcode integration: https://kotlinlang.org/docs/multiplatform/multiplatform-direct-integration.html
- Compose Multiplatform app entry points: https://kotlinlang.org/docs/multiplatform/compose-multiplatform-entry-points.html
- Kotlin Multiplatform iOS project setup: https://kotlinlang.org/docs/multiplatform/compose-multiplatform-getting-started.html
- Apple media playback configuration (AVAudioSession and background mode): https://developer.apple.com/documentation/avfoundation/configuring-your-app-for-media-playback
