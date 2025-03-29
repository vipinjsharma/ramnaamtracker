#!/bin/bash

echo "==========================================================="
echo "Ram Lekhak Android App Builder"
echo "==========================================================="

# Create export directory
EXPORT_DIR="ram_lekhak_web_app"
mkdir -p $EXPORT_DIR

# Also create Android assets directory
ANDROID_ASSETS_DIR="app/src/main/assets"
mkdir -p $ANDROID_ASSETS_DIR

# Copy main web app files
echo "Copying web app files..."
cp index.html app.js styles.css translation-fix.js $EXPORT_DIR/
cp index.html app.js styles.css translation-fix.js $ANDROID_ASSETS_DIR/

# Copy public folder if it exists
if [ -d "public" ]; then
  mkdir -p $EXPORT_DIR/public
  mkdir -p $ANDROID_ASSETS_DIR/public
  cp -r public/* $EXPORT_DIR/public/ 2>/dev/null
  cp -r public/* $ANDROID_ASSETS_DIR/public/ 2>/dev/null
  echo "Public folder copied."
fi

# Copy images folder if it exists
if [ -d "images" ]; then
  mkdir -p $EXPORT_DIR/images
  mkdir -p $ANDROID_ASSETS_DIR/images
  cp -r images/* $EXPORT_DIR/images/ 2>/dev/null
  cp -r images/* $ANDROID_ASSETS_DIR/images/ 2>/dev/null
  echo "Images folder copied."
fi

# Copy fonts folder if it exists
if [ -d "fonts" ]; then
  mkdir -p $EXPORT_DIR/fonts
  mkdir -p $ANDROID_ASSETS_DIR/fonts
  cp -r fonts/* $EXPORT_DIR/fonts/ 2>/dev/null
  cp -r fonts/* $ANDROID_ASSETS_DIR/fonts/ 2>/dev/null
  echo "Fonts folder copied."
fi

# Copy translations folder if it exists
if [ -d "translations" ]; then
  mkdir -p $EXPORT_DIR/translations
  mkdir -p $ANDROID_ASSETS_DIR/translations
  cp -r translations/* $EXPORT_DIR/translations/ 2>/dev/null
  cp -r translations/* $ANDROID_ASSETS_DIR/translations/ 2>/dev/null
  echo "Translations folder copied."
fi

echo "Web app files copied to Android assets directory."
echo "==========================================================="
echo "Attempting to build APK..."

# Try to build the APK
if [ -f "./gradlew" ]; then
  echo "Building debug APK..."
  chmod +x ./gradlew
  ./gradlew assembleDebug
  
  # Check if APK was built successfully
  if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
    echo "✅ Debug APK built successfully!"
    echo "APK Location: app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "To install on a connected device, run:"
    echo "adb install -r app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "To build a release APK for Play Store submission:"
    echo "1. Update app/build.gradle with your signing configuration"
    echo "2. Run: ./gradlew assembleRelease"
  else
    echo "❌ APK build failed or APK not found."
    echo "Check the build logs for errors."
  fi
else
  echo "Gradle wrapper not found. Cannot build APK automatically."
  echo "To build the APK:"
  echo "1. Open this project in Android Studio"
  echo "2. Let Gradle sync complete"
  echo "3. Click Build > Build Bundle(s) / APK(s) > Build APK(s)"
fi

# Create detailed Android instructions file
cat > $EXPORT_DIR/ANDROID_PUBLISHING_GUIDE.md << 'EOF'
# Ram Lekhak Android App Publishing Guide

This comprehensive guide will help you publish your Ram Lekhak app to the Google Play Store.

## Prerequisites

1. [Android Studio](https://developer.android.com/studio) installed
2. A [Google Play Developer account](https://play.google.com/console/signup) ($25 one-time registration fee)
3. Your app's assets (already included in this package)

## Step 1: Prepare Your App for Release

### Configure App Details

1. Open `app/build.gradle` and update:
   ```gradle
   android {
       defaultConfig {
           applicationId "com.ramlekhak"  // Change to your unique package name
           versionCode 1                  // Increment for each new release
           versionName "1.0"              // User-visible version number
       }
   }
   ```

2. Update app name in `app/src/main/res/values/strings.xml`

3. Customize app icon:
   - Create icons using [Image Asset Studio](https://developer.android.com/studio/write/image-asset-studio)
   - Or replace files in `app/src/main/res/mipmap-*` directories

### Create a Signing Key

1. In Android Studio, go to **Build** > **Generate Signed Bundle/APK**
2. Select **APK** and click **Next**
3. Click **Create new...** to create a new keystore
4. Fill in the form:
   - Keystore path: Choose a secure location
   - Password: Create a strong password
   - Key alias: A name for your key
   - Key password: Can be the same as the keystore password
   - Certificate information: Fill in your details
5. Click **OK** and then **Next**

**IMPORTANT**: Keep your keystore file and password secure. If lost, you won't be able to update your app!

## Step 2: Build a Release APK

1. In Android Studio, go to **Build** > **Generate Signed Bundle/APK**
2. Select **APK** and click **Next**
3. Fill in your keystore details and click **Next**
4. Select **release** build variant
5. Check **V1 (Jar Signature)** and **V2 (Full APK Signature)**
6. Click **Finish**

The signed APK will be created in `app/release/app-release.apk`

## Step 3: Test Your Release Build

Before uploading to Google Play:

1. Install the release APK on a physical device:
   ```
   adb install -r app/release/app-release.apk
   ```

2. Test thoroughly to ensure:
   - All features work correctly
   - WebView loads properly
   - No crashes or performance issues
   - Proper handling of screen rotations

## Step 4: Prepare Store Listing Assets

Create these required assets:

1. **Screenshots**: 
   - At least 2 phone screenshots (16:9 aspect ratio)
   - Recommended: 8 screenshots showing different app features
   - Resolution: at least 1080 pixels wide

2. **Feature Graphic** (1024 x 500 px):
   - Banner image displayed at the top of your store listing

3. **App Icon** (512 x 512 px):
   - High-resolution version of your app icon

4. **Short Description** (80 characters max):
   - Concise summary of your app

5. **Full Description** (4000 characters max):
   - Detailed description with features and benefits

## Step 5: Create Google Play Store Listing

1. Log in to [Google Play Console](https://play.google.com/console/)
2. Click **Create app**
3. Enter app details:
   - App name: "Ram Lekhak"
   - Default language
   - App or Game: App
   - Free or Paid
4. Click **Create app**

## Step 6: Complete Store Listing

Navigate through each section:

1. **App details**:
   - Add descriptions, screenshots, feature graphic
   - Add contact details and privacy policy URL
   
2. **Content rating**:
   - Complete the questionnaire
   - Ram Lekhak should receive a suitable rating

3. **Pricing & distribution**:
   - Select countries for distribution
   - Confirm compliance with export laws and platform requirements

## Step 7: Upload and Release Your App

1. Go to **Production** > **Create new release**
2. Click **Continue**
3. Upload your APK or App Bundle
4. Add release notes
5. Click **Save** and then **Review release**
6. Click **Start rollout to Production**

## Step 8: Monitor Your App

After release, regularly check:

1. **Crashes and ANRs** for stability issues
2. **User feedback** in reviews
3. **Statistics** for installs and active users

## Updating Your App

To release updates:

1. Increment `versionCode` and update `versionName` in `build.gradle`
2. Make your changes and build a new signed APK
3. Create a new release in Google Play Console
4. Upload the new APK and add release notes
5. Roll out the update

## Advanced Features to Consider

- **In-app purchases**: For premium features
- **Firebase Analytics**: To track user behavior
- **Push notifications**: To re-engage users
- **App bundles**: Smaller, optimized downloads

---

For detailed help, visit the [Google Play Console Help Center](https://support.google.com/googleplay/android-developer/).
EOF

echo "==========================================================="
echo "✅ Process completed!"
echo "Web files copied to Android assets: $ANDROID_ASSETS_DIR/"
echo "See ANDROID_PUBLISHING_GUIDE.md for detailed Play Store publishing instructions."
echo "==========================================================="