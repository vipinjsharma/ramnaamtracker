#!/bin/bash

echo "==========================================================="
echo "Ram Lekhak Web App Exporter for Android"
echo "==========================================================="

# Create export directory
EXPORT_DIR="ram_lekhak_web_app"
mkdir -p $EXPORT_DIR

# Copy main web app files
echo "Copying web app files..."
cp index.html app.js styles.css $EXPORT_DIR/

# Copy public folder if it exists
if [ -d "public" ]; then
  mkdir -p $EXPORT_DIR/public
  cp -r public/* $EXPORT_DIR/public/ 2>/dev/null
  echo "Public folder copied."
fi

# Create a README file with instructions
cat > $EXPORT_DIR/README.txt << EOF
==================== Ram Lekhak Web App Files ====================

These files are ready to be included in your Android WebView app.

To build your Android app:
1. Create a new Android project in Android Studio
2. Copy these files to the app/src/main/assets/ directory
3. Use WebView to load the index.html file from assets

Sample Android WebView code:
----------------------------
// Inside your activity's onCreate method:
WebView webView = findViewById(R.id.webView);
webView.getSettings().setJavaScriptEnabled(true);
webView.loadUrl("file:///android_asset/index.html");

=================================================================
EOF

# Zip the files for easy download
echo "Creating zip archive..."
zip -r ram_lekhak_web_app.zip $EXPORT_DIR

echo "==========================================================="
echo "✅ Export completed!"
echo "==========================================================="
echo "Files exported to: $EXPORT_DIR/"
echo "Zip archive created: ram_lekhak_web_app.zip"
echo "Download these files and import them into your Android Studio project."
echo "==========================================================="