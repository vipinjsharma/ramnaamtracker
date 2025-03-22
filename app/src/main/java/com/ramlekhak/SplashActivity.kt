package com.ramlekhak

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_TIME: Long = 1500 // 1.5 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        
        // Use a handler to delay loading the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            
            // Close splash activity
            finish()
        }, SPLASH_DISPLAY_TIME)
    }
}