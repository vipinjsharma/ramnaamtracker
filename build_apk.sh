#!/bin/bash

echo "Building Ram Lekhak APK..."

# Set Android SDK environment variables
export ANDROID_HOME=/home/runner/android-sdk
export PATH=$PATH:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

# Copy latest web app files to Android assets
echo "Copying web app files to Android assets..."
mkdir -p app/src/main/assets/
cp index.html app.js styles.css app/src/main/assets/
mkdir -p app/src/main/assets/public/
cp -r public/* app/src/main/assets/public/ 2>/dev/null || true

# Update local.properties
echo "Configuring Android SDK..."
echo "sdk.dir=$ANDROID_HOME" > local.properties

# Run Gradle build
echo "Building debug APK..."
chmod +x ./gradlew
./gradlew assembleDebug --stacktrace

# Check if build succeeded
if [ -f app/build/outputs/apk/debug/app-debug.apk ]; then
    echo "APK build successful!"
    echo "APK location: app/build/outputs/apk/debug/app-debug.apk"
    
    # Create a directory for the final APK
    mkdir -p build_output
    
    # Copy APK to a more accessible location
    cp app/build/outputs/apk/debug/app-debug.apk build_output/ram_lekhak.apk
    
    echo "APK saved to: build_output/ram_lekhak.apk"
else
    echo "APK build failed. Check the logs for errors."
fi