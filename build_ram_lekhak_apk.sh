#!/bin/bash

echo "========================================================"
echo "Ram Lekhak APK Builder"
echo "========================================================"

# Setup Android SDK environment
export ANDROID_HOME=~/android-sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# Create necessary directories
mkdir -p $ANDROID_HOME/licenses

# Accept Android SDK licenses
echo "Accepting Android SDK licenses..."
echo "24333f8a63b6825ea9c5514f83c2829b004d1fee" > $ANDROID_HOME/licenses/android-sdk-license
echo "d56f5187479451eabf01fb78af6dfcb131a6481e" >> $ANDROID_HOME/licenses/android-sdk-license
echo "84831b9409646a918e30573bab4c9c91346d8abd" > $ANDROID_HOME/licenses/android-sdk-preview-license

# Create local.properties file
echo "Configuring Android SDK location..."
echo "sdk.dir=$ANDROID_HOME" > local.properties

# Copy the latest web app files to Android assets
echo "Copying web app files to Android assets..."
mkdir -p app/src/main/assets/
cp index.html app.js styles.css app/src/main/assets/

# Copy public folder if it exists
if [ -d "public" ]; then
  mkdir -p app/src/main/assets/public/
  cp -r public/* app/src/main/assets/public/ 2>/dev/null || true
  echo "Public folder copied."
fi

# Make gradlew executable
chmod +x ./gradlew

# Build the APK
echo "Building debug APK..."
./gradlew assembleDebug --stacktrace

# Check if build succeeded
if [ -f app/build/outputs/apk/debug/app-debug.apk ]; then
    echo "========================================================"
    echo "✅ APK build successful!"
    echo "========================================================"
    echo "APK location: app/build/outputs/apk/debug/app-debug.apk"
    
    # Create a directory for the final APK
    mkdir -p build_output
    
    # Copy APK to a more accessible location with a better name
    cp app/build/outputs/apk/debug/app-debug.apk build_output/ram_lekhak.apk
    
    echo "APK saved to: build_output/ram_lekhak.apk"
    echo "You can download this file from the Files panel in Replit."
else
    echo "========================================================"
    echo "❌ APK build failed. Check the logs for errors."
    echo "========================================================"
fi