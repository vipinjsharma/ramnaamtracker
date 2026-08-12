package com.ramlekhak

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_TIME: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        
        // Use a handler to delay loading the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start main activity with a fade transition
            val intent = Intent(this, MainActivity::class.java)
            val options = ActivityOptionsCompat.makeCustomAnimation(
                this,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
            
            // Close splash activity
            finish()
        }, SPLASH_DISPLAY_TIME)
    }
}