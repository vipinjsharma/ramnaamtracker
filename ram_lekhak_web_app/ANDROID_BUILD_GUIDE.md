# Ram Lekhak Android App Build Guide

This guide will walk you through the process of building the Ram Lekhak Android app from your exported web app files. The app uses a WebView to display the web content.

## Prerequisites

- [Android Studio](https://developer.android.com/studio) (latest version)
- Basic familiarity with Android development
- The exported Ram Lekhak web app files (included in this folder)

## Step 1: Create a New Android Project

1. Open Android Studio and select "New Project"
2. Choose "Empty Activity" and click "Next"
3. Configure your project:
   - Name: "Ram Lekhak"
   - Package name: "com.ramlekhak"
   - Save location: Choose where to save your project
   - Language: Kotlin
   - Minimum SDK: API 21 (Android 5.0 Lollipop) or higher
4. Click "Finish" to create the project

## Step 2: Add Web App Files to Android Assets

1. In Android Studio, locate the "app/src/main" folder in the Project pane
2. Right-click on the "main" folder and select "New > Directory"
3. Enter "assets" as the directory name and click "OK"
4. Copy all files from this exported web app folder to the assets directory
   - `index.html`
   - `app.js`
   - `styles.css`
   - `public/` folder (if present)

## Step 3: Configure the MainActivity

1. Open `MainActivity.kt` and replace its contents with the code below:

```kotlin
package com.ramlekhak

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import java.util.Locale
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.webkit.WebViewCompat
import androidx.webkit.WebViewFeature

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var errorLayout: LinearLayout
    private lateinit var retryButton: Button
    private lateinit var errorTextView: TextView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        errorLayout = findViewById(R.id.errorLayout)
        retryButton = findViewById(R.id.retryButton)
        errorTextView = findViewById(R.id.errorTextView)

        // Set retry button click listener
        retryButton.setOnClickListener {
            errorLayout.visibility = View.GONE
            loadWebApp()
        }

        setupWebView()
        setupSwipeRefresh()

        // Load the web app
        if (savedInstanceState == null) {
            loadWebApp()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        // Configure WebView settings
        with(webView.settings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            allowFileAccess = true
            allowContentAccess = true
            databaseEnabled = true
            
            // Use modern web engine if available
            setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            
            // Set user agent to ensure proper rendering
            userAgentString = "$userAgentString RamLekhakApp"

            // Enable mixed content for development (remove for production)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            
            // Enable modern features if available
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK) && 
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                WebViewCompat.setForceDark(webView, WebSettings.FORCE_DARK_AUTO)
            }
        }

        // Set WebView client for handling page navigation
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // Handle external links
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    if (!url.contains(Uri.parse("file:///android_asset").toString())) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    }
                }
                return false
            }
            
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar.visibility = View.VISIBLE
                errorLayout.visibility = View.GONE
                super.onPageStarted(view, url, favicon)
            }
            
            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                
                // Add a class to the body to indicate we're on Android
                view?.evaluateJavascript(
                    "document.body.classList.add('android-app');",
                    null
                )
                
                super.onPageFinished(view, url)
            }
            
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                if (request?.isForMainFrame == true) {
                    showErrorView("Error loading page")
                }
                super.onReceivedError(view, request, error)
            }
        }

        // Set WebChromeClient for handling JavaScript alerts and progress
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                } else {
                    progressBar.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
            
            override fun onJsAlert(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage(message)
                    .setPositiveButton("OK") { _, _ -> result?.confirm() }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }
            
            override fun onJsConfirm(
                view: WebView?,
                url: String?,
                message: String?,
                result: JsResult?
            ): Boolean {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage(message)
                    .setPositiveButton("Yes") { _, _ -> result?.confirm() }
                    .setNegativeButton("No") { _, _ -> result?.cancel() }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }
        }

        // Setup JavaScript interface to bridge web and native code
        webView.addJavascriptInterface(WebAppInterface(this), "AndroidInterface")
    }

    private fun setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            loadWebApp()
        }
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    private fun loadWebApp() {
        progressBar.visibility = View.VISIBLE
        // Detect system language and pass it as a URL parameter
        val deviceLanguage = getCurrentLanguage()
        webView.loadUrl("file:///android_asset/index.html?lang=$deviceLanguage")
    }
    
    // Making this public so WebAppInterface can access it
    fun getCurrentLanguage(): String {
        val locale = Locale.getDefault()
        return when (locale.language) {
            "hi" -> "hi" // Hindi
            else -> "en" // Default to English
        }
    }
    
    private fun showErrorView(errorMessage: String) {
        webView.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
        errorTextView.text = errorMessage
        progressBar.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }

    override fun onBackPressed() {
        when {
            errorLayout.visibility == View.VISIBLE -> {
                // If error view is showing, retry loading the app
                errorLayout.visibility = View.GONE
                loadWebApp()
            }
            webView.canGoBack() -> {
                // If WebView can go back, navigate back in WebView history
                webView.goBack()
            }
            else -> {
                // Otherwise, show exit confirmation
                showExitConfirmationDialog()
            }
        }
    }
    
    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you sure you want to exit?")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }
}

// JavaScript interface to provide native functionality to web app
class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun showToast(message: String) {
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
    }
    
    @JavascriptInterface
    fun getDeviceInfo(): String {
        return "Android ${Build.VERSION.RELEASE}"
    }
    
    @JavascriptInterface
    fun getDeviceLanguage(): String {
        return (context as? MainActivity)?.getCurrentLanguage() ?: "en"
    }
    
    @JavascriptInterface
    fun isAdminEnabled(): Boolean {
        // By default, admin is disabled in app
        return false
    }
    
    @JavascriptInterface
    fun shareText(title: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(Intent.createChooser(intent, "Share Ram Lekhak Stats"))
    }
    
    @JavascriptInterface
    fun vibrate() {
        // Default vibration with medium intensity
        vibrate(50, "medium")
    }
    
    @JavascriptInterface
    fun vibrate(duration: Int, intensity: String) {
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as android.os.VibratorManager
                vibratorManager.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Set vibration amplitude based on intensity
                val amplitude = when (intensity) {
                    "high" -> android.os.VibrationEffect.DEFAULT_AMPLITUDE
                    "medium" -> (android.os.VibrationEffect.DEFAULT_AMPLITUDE * 0.7).toInt()
                    "low" -> (android.os.VibrationEffect.DEFAULT_AMPLITUDE * 0.4).toInt()
                    else -> android.os.VibrationEffect.DEFAULT_AMPLITUDE
                }
                
                // Create different patterns based on intensity
                when (intensity) {
                    "high" -> {
                        // Strong double pulse for high precision
                        val timings = longArrayOf(0, duration.toLong(), 50, duration.toLong())
                        val amplitudes = intArrayOf(0, amplitude, 0, amplitude)
                        vibrator.vibrate(android.os.VibrationEffect.createWaveform(timings, amplitudes, -1))
                    }
                    "low" -> {
                        // Short single pulse for low precision
                        vibrator.vibrate(android.os.VibrationEffect.createOneShot((duration * 0.5).toLong(), amplitude))
                    }
                    else -> {
                        // Medium single pulse for medium precision
                        vibrator.vibrate(android.os.VibrationEffect.createOneShot(duration.toLong(), amplitude))
                    }
                }
            } else {
                // For older Android versions, just use duration
                @Suppress("DEPRECATION")
                when (intensity) {
                    "high" -> vibrator.vibrate(longArrayOf(0, duration.toLong(), 50, duration.toLong()), -1)
                    "low" -> vibrator.vibrate((duration * 0.5).toLong())
                    else -> vibrator.vibrate(duration.toLong())
                }
            }
        } catch (e: Exception) {
            // Ignore vibration errors
        }
    }
}
```

## Step 4: Create the Layout XML

1. Open `res/layout/activity_main.xml` and replace its contents with:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.swiperefreshlayout.widget.SwipeRefreshLayout
        android:id="@+id/swipeRefreshLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <WebView
            android:id="@+id/webView"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </androidx.swiperefreshlayout.widget.SwipeRefreshLayout>

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="match_parent"
        android:layout_height="5dp"
        android:indeterminate="true"
        android:visibility="gone"
        app:layout_constraintTop_toTopOf="parent" />

    <LinearLayout
        android:id="@+id/errorLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:orientation="vertical"
        android:padding="16dp"
        android:visibility="gone"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <TextView
            android:id="@+id/errorTextView"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:text="Something went wrong"
            android:textSize="18sp" />

        <Button
            android:id="@+id/retryButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Retry" />

    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Step 5: Add Required Dependencies

1. Open `app/build.gradle` and add these dependencies:

```gradle
dependencies {
    // Existing dependencies...
    
    // SwipeRefreshLayout
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    
    // WebView extensions for backward compatibility
    implementation 'androidx.webkit:webkit:1.6.0'
}
```

## Step 6: Configure AndroidManifest.xml

1. Open `AndroidManifest.xml` and update it to include internet permissions:

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="Ram Lekhak"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.RamLekhak">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## Step 7: Create App Icon (Optional)

1. Right-click on the `res` folder and select "New > Image Asset"
2. Choose "Launcher Icons" and customize it with your Ram Lekhak icon
3. Follow the wizard to create your launcher icons

## Step 8: Build and Run the App

1. Connect your Android device or start an emulator
2. Click on the "Run" button (green triangle) in Android Studio
3. Select your device and click "OK"
4. Wait for the app to build and install on your device

## Step 9: Generate a Signed APK for Google Play Store

1. In Android Studio, go to "Build > Generate Signed Bundle/APK"
2. Select "APK" and click "Next"
3. Create a new keystore or use an existing one
   - If creating new: Fill in the key store path, password, and key details
   - If using existing: Select your keystore file and enter password
4. Click "Next"
5. Select "release" as build variant
6. Check both "V1" and "V2" signature versions
7. Click "Finish"
8. The signed APK will be created in `app/release/app-release.apk`

## Troubleshooting

- **WebView not loading**: Make sure you have the correct file paths in your assets folder
- **JavaScript not working**: Verify that JavaScript is enabled with `webView.settings.javaScriptEnabled = true`
- **App crashes on startup**: Check Logcat for stack traces to identify the issue
- **Missing resources**: Ensure all web app files (HTML, CSS, JS) are properly copied to the assets folder

---

You've now built the Ram Lekhak Android app! This app wraps your web application in a native Android container, providing a seamless experience for your users on Android devices.