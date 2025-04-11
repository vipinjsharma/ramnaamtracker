package com.ramlekhak

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
        setContentView(R.layout.activity_webview)

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
            
            // Explicitly disable dark mode for consistent light theme
            if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK) && 
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                WebViewCompat.setForceDark(webView, WebSettings.FORCE_DARK_OFF)
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
                    showErrorView(getString(R.string.error_loading))
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
                    .setPositiveButton(getString(R.string.ok)) { _, _ -> result?.confirm() }
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
                    .setPositiveButton(getString(R.string.yes)) { _, _ -> result?.confirm() }
                    .setNegativeButton(getString(R.string.no)) { _, _ -> result?.cancel() }
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
        if (isNetworkAvailable()) {
            progressBar.visibility = View.VISIBLE
            // Detect system language and pass it as a URL parameter
            val deviceLanguage = getCurrentLanguage()
            webView.loadUrl("file:///android_asset/index.html?lang=$deviceLanguage")
        } else {
            showErrorView(getString(R.string.no_internet))
        }
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
    
    private fun isNetworkAvailable(): Boolean {
        // For loading local assets, we don't necessarily need network
        // But we'll keep this function for future use if we need online resources
        return true
        
        /*
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
        */
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
            .setTitle(getString(R.string.exit_title))
            .setMessage(getString(R.string.exit_message))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> finish() }
            .setNegativeButton(getString(R.string.no), null)
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
        val shareTitle = (context as? MainActivity)?.getString(R.string.share_title) ?: "Share Ram Lekhak Stats"
        context.startActivity(Intent.createChooser(intent, shareTitle))
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