# Ram Lekhak Android App - Build Guide

This guide explains how to build the Ram Lekhak Android app from the prepared project.

## Prerequisites

- [Android Studio](https://developer.android.com/studio) (latest version)
- JDK 11 or newer
- A physical Android device or emulator for testing
- Basic familiarity with Android development

## Steps to Build the App

### 1. Clone/Download the Project

- Download this entire project folder to your local machine

### 2. Open the Project in Android Studio

- Open Android Studio
- Select "Open an existing Android Studio project"
- Navigate to the downloaded project folder and select it
- Wait for Gradle sync to complete

### 3. Verify Web App Files

The web app files have already been copied to the Android assets directory:
- `app/src/main/assets/index.html`
- `app/src/main/assets/app.js`
- `app/src/main/assets/styles.css`
- `app/src/main/assets/translation-fix.js`

### 4. Important Updates Made

The following important changes have been made to the project:

1. **Dark Mode Removed**: The app now uses only light mode themes
   - Dark theme options have been removed from the UI
   - Theme normalization logic has been updated
   - WebView's `FORCE_DARK` setting is explicitly set to `OFF`

2. **Theme Changes**:
   - All themes (Ram, Krishna, Lakshmi, etc.) are properly configured

### 5. Build the Debug APK

- Connect your Android device via USB (enable USB debugging in developer options)
- In Android Studio, select "Run" > "Run 'app'" from the menu
- Select your device from the deployment target dialog
- Wait for the build to complete and the app to install

Alternatively, to build the APK without installing:
- Select "Build" > "Build Bundle(s) / APK(s)" > "Build APK(s)"
- The APK will be available at `app/build/outputs/apk/debug/app-debug.apk`

### 6. Build a Release APK for Google Play Store

To create a signed release version for the Play Store:

1. In Android Studio, select "Build" > "Generate Signed Bundle/APK"
2. Select "APK" and click "Next"
3. Create a new keystore or use an existing one:
   - If creating new: Fill in the key store path, password, key alias, and certificate information
   - If using existing: Browse to your keystore file and enter the required passwords
4. Click "Next"
5. Select "release" build variant
6. Click "Finish"

The signed APK will be created in `app/release/app-release.apk`

**Important**: Keep your keystore file and password secure. If you lose them, you won't be able to update your app on the Play Store in the future.

### 7. Troubleshooting

- **Build Errors**: Try "File" > "Sync Project with Gradle Files" and "Build" > "Clean Project"
- **App Crashes**: Check Logcat for stack traces
- **WebView Issues**: Verify all web app files are correctly placed in the assets folder

## Additional Configuration Options

### App Name & Package

- App name can be changed in `app/src/main/res/values/strings.xml`
- Package name is defined in `app/build.gradle` as `applicationId`

### Launcher Icon

- Replace icon files in `app/src/main/res/mipmap-*` directories
- Or use Android Studio's "Image Asset Studio": Right-click on `app/src/main/res` > "New" > "Image Asset"

## Testing

Before publishing, thoroughly test:
- All theme options work correctly
- Language switching between English and Hindi
- Character writing functionality
- Stats tracking and display
- Menu and settings options

## Distribution

Follow the steps in the included `ram_lekhak_web_app/ANDROID_PUBLISHING_GUIDE.md` for detailed instructions on publishing to the Google Play Store.