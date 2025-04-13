package com.ramlekhak.ui.rating

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ramlekhak.R

class RatingActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var submitButton: Button
    private lateinit var laterButton: Button
    private lateinit var statusText: TextView
    private lateinit var titleText: TextView

    private val sharedPreferences by lazy {
        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        initializeViews()
        setupClickListeners()
        checkPreviousRating()
        incrementAppUsageCount()
    }

    private fun initializeViews() {
        ratingBar = findViewById(R.id.rating_bar)
        submitButton = findViewById(R.id.submit_button)
        laterButton = findViewById(R.id.later_button)
        statusText = findViewById(R.id.status_text)
        titleText = findViewById(R.id.rating_title)
    }

    private fun setupClickListeners() {
        submitButton.setOnClickListener {
            val rating = ratingBar.rating
            if (rating > 0) {
                saveRating(rating)
                if (rating >= 4) {
                    openPlayStore()
                }
                finish()
            } else {
                statusText.text = getString(R.string.select_rating)
            }
        }

        laterButton.setOnClickListener {
            finish()
        }
    }

    private fun saveRating(rating: Float) {
        with(sharedPreferences.edit()) {
            putFloat(KEY_APP_RATING, rating)
            putBoolean(KEY_APP_RATED, true)
            apply()
        }
        // You could also send this rating to your backend if needed
    }

    private fun openPlayStore() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=${packageName}")
            startActivity(intent)
        } catch (e: Exception) {
            // If Play Store app is not available, open in browser
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=${packageName}")
            startActivity(intent)
        }
    }

    private fun checkPreviousRating() {
        val previousRating = sharedPreferences.getFloat(KEY_APP_RATING, 0f)
        if (previousRating > 0) {
            ratingBar.rating = previousRating
            statusText.text = getString(R.string.rating_thanks, previousRating.toInt())
        }
    }

    private fun incrementAppUsageCount() {
        val currentCount = sharedPreferences.getInt(KEY_APP_USAGE_COUNT, 0)
        sharedPreferences.edit().putInt(KEY_APP_USAGE_COUNT, currentCount + 1).apply()
    }

    companion object {
        private const val PREFS_NAME = "RamLekhakPrefs"
        private const val KEY_APP_RATING = "app_rating"
        private const val KEY_APP_RATED = "app_rated"
        private const val KEY_APP_USAGE_COUNT = "app_usage_count"

        // Method to check if we should show the rating dialog
        fun shouldShowRatingPrompt(context: Context): Boolean {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val hasRated = prefs.getBoolean(KEY_APP_RATED, false)
            val usageCount = prefs.getInt(KEY_APP_USAGE_COUNT, 0)
            
            // Show rating prompt if user hasn't rated and has used the app at least 5 times
            return !hasRated && usageCount >= 5
        }
    }
} 