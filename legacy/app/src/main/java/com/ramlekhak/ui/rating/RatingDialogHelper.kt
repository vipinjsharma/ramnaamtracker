package com.ramlekhak.ui.rating

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.edit
import com.ramlekhak.R
import java.util.Calendar
import java.util.Date

class RatingDialogHelper(private val context: Context) {
    
    private var dialog: Dialog? = null
    private lateinit var closeButton: ImageView
    private lateinit var starViews: List<ImageView>
    private lateinit var commentInput: EditText
    private lateinit var submitButton: Button
    
    private var currentRating: Float = 0f
    private var onRatingSubmittedListener: ((RatingData) -> Unit)? = null
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    data class RatingData(
        val rating: Float,
        val comment: String,
        val timestamp: Date
    )
    
    fun setOnRatingSubmittedListener(listener: (RatingData) -> Unit) {
        onRatingSubmittedListener = listener
    }
    
    fun showDialog() {
        if (dialog == null) {
            initDialog()
        }
        
        resetDialog()
        dialog?.show()
    }
    
    private fun initDialog() {
        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_rating)
            setCancelable(true)
            
            // Initialize views
            closeButton = findViewById(R.id.closeButton)
            commentInput = findViewById(R.id.commentInput)
            submitButton = findViewById(R.id.submitButton)
            
            // Initialize star views
            starViews = listOf(
                findViewById(R.id.star1),
                findViewById(R.id.star2),
                findViewById(R.id.star3),
                findViewById(R.id.star4),
                findViewById(R.id.star5)
            )
            
            setupListeners()
        }
    }
    
    private fun setupListeners() {
        // Close button
        closeButton.setOnClickListener {
            dialog?.dismiss()
        }
        
        // Star rating
        starViews.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                // Set rating (index + 1 since ratings are 1-5)
                setRating(index + 1f)
            }
        }
        
        // Submit button
        submitButton.setOnClickListener {
            if (validateRating()) {
                submitRating()
            }
        }
    }
    
    private fun setRating(rating: Float) {
        currentRating = rating
        
        // Update star images based on rating
        for (i in 0 until starViews.size) {
            val starPosition = i + 1 // Stars are 1-based
            
            if (starPosition <= rating) {
                // Full star
                starViews[i].setImageResource(R.drawable.ic_star_filled)
                starViews[i].imageTintList = android.content.res.ColorStateList.valueOf(Color.parseColor("#E68656"))
            } else if (starPosition - 0.5f <= rating && starPosition > rating) {
                // Half star (if we want to support partial ratings)
                starViews[i].setImageResource(R.drawable.ic_star_half)
                starViews[i].imageTintList = android.content.res.ColorStateList.valueOf(Color.parseColor("#E68656"))
            } else {
                // Empty star
                starViews[i].setImageResource(R.drawable.ic_star_outline)
                starViews[i].imageTintList = android.content.res.ColorStateList.valueOf(Color.parseColor("#E0E0E0"))
            }
        }
        
        // Enable submit button if rating is > 0
        submitButton.isEnabled = currentRating > 0
    }
    
    private fun validateRating(): Boolean {
        if (currentRating <= 0) {
            Toast.makeText(context, "Please select a star rating", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    
    private fun submitRating() {
        val comment = commentInput.text.toString().trim()
        val ratingData = RatingData(
            rating = currentRating,
            comment = comment,
            timestamp = Calendar.getInstance().time
        )
        
        // Save that user has rated the app
        saveRatingRecord(ratingData)
        
        // Notify listener
        onRatingSubmittedListener?.invoke(ratingData)
        
        // Show thank you message and dismiss
        Toast.makeText(context, "Thank you for your feedback!", Toast.LENGTH_SHORT).show()
        dialog?.dismiss()
    }
    
    private fun resetDialog() {
        currentRating = 0f
        commentInput.setText("")
        submitButton.isEnabled = false
        
        // Reset stars to empty
        starViews.forEach { starView ->
            starView.setImageResource(R.drawable.ic_star_outline)
            starView.imageTintList = android.content.res.ColorStateList.valueOf(Color.parseColor("#E0E0E0"))
        }
    }
    
    private fun saveRatingRecord(ratingData: RatingData) {
        prefs.edit {
            putFloat(KEY_LAST_RATING, ratingData.rating)
            putLong(KEY_LAST_RATING_TIME, ratingData.timestamp.time)
            putBoolean(KEY_HAS_RATED, true)
        }
    }
    
    fun shouldShowRatingDialog(usageCount: Int): Boolean {
        // Don't show if already rated in the last month
        if (hasRatedRecently()) {
            return false
        }
        
        // Show if used app multiple times
        return usageCount >= USAGE_THRESHOLD
    }
    
    private fun hasRatedRecently(): Boolean {
        val hasRated = prefs.getBoolean(KEY_HAS_RATED, false)
        
        if (!hasRated) {
            return false
        }
        
        val lastRatingTime = prefs.getLong(KEY_LAST_RATING_TIME, 0)
        if (lastRatingTime == 0L) {
            return false
        }
        
        // Check if last rating was less than a month ago
        val currentTime = Calendar.getInstance().timeInMillis
        val oneMonthInMillis = 30L * 24 * 60 * 60 * 1000 // 30 days
        
        return (currentTime - lastRatingTime) < oneMonthInMillis
    }
    
    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }
    
    companion object {
        private const val PREFS_NAME = "RatingPreferences"
        private const val KEY_LAST_RATING = "last_rating"
        private const val KEY_LAST_RATING_TIME = "last_rating_time"
        private const val KEY_HAS_RATED = "has_rated"
        private const val USAGE_THRESHOLD = 5 // Show after 5 uses
    }
} 