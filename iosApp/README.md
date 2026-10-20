# SimpMusic iOS host app

This Xcode project hosts the shared Compose Multiplatform UI in `:composeApp`.

## Before building

- Use macOS with Xcode and an installed iOS Simulator SDK (or a connected device).
- Install JDK 21. The Xcode build script tries `/usr/libexec/java_home -v 21` when `JAVA_HOME` is unset; otherwise set `JAVA_HOME` in the Xcode build environment. The repository wrapper downloads Gradle 9.8.1 when needed.
- Keep signing configuration user-owned; no signing identity or Team ID is committed in this project.
- The unsigned simulator CI workflow is `.github/workflows/ios-simulator.yml`; it compiles the device framework, runs iOS simulator tests, builds the simulator app, and uploads the `.app` artifact. It does not sign or produce a device-installable archive.

## Open/build on macOS

1. Open `iosApp/SimpMusic.xcodeproj` in Xcode.
2. Select the `SimpMusic` target, choose your signing team, and set a unique bundle identifier if needed.
3. Select an iOS simulator or device and build/run.

The Xcode Run Script phase invokes `:composeApp:embedAndSignAppleFrameworkForXcode` from the repository root before compiling the SwiftUI host. The shared framework is named `ComposeApp` to match the Swift import.

## Current port status

The Xcode host, Kotlin iOS targets, and shared Compose platform declarations are in place. A minimal AVPlayer path now resolves and plays audio streams, and iOS Brotli response decoding uses a Kotlin/Native-compatible codec. Neither has been compiled or run on an Apple host yet. Radio/related-track loading, playback persistence, casting, and some platform integrations remain incomplete; treat the app as a development port, not a release.
