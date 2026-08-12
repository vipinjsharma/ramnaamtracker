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
