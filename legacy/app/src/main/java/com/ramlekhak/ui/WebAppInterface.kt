package com.ramlekhak.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import java.util.Locale

class WebAppInterface(private val context: Context) {
    
    @JavascriptInterface
    fun vibrate(duration: Long = 50) {
        val vibrator = getSystemService(context, Vibrator::class.java)
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                it.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(duration)
            }
        }
    }

    @JavascriptInterface
    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun shareProgress(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    @JavascriptInterface
    fun getDeviceLanguage(): String {
        return Locale.getDefault().language
    }

    @JavascriptInterface
    fun openPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse(
                "https://play.google.com/store/apps/details?id=${context.packageName}"
            )
        }
        context.startActivity(intent)
    }

    @JavascriptInterface
    fun getSystemTheme(): String {
        return if (context.resources.configuration.uiMode and 
            android.content.res.Configuration.UI_MODE_NIGHT_MASK == 
            android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            "dark"
        } else {
            "light"
        }
    }
} 