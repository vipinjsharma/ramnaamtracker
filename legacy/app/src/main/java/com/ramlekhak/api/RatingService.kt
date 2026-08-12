package com.ramlekhak.api

import com.ramlekhak.ui.rating.RatingDialogHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for submitting app ratings to the backend.
 * In a real app, this would use Retrofit or another HTTP client to communicate with the server.
 */
@Singleton
class RatingService @Inject constructor() {
    
    /**
     * Submit a rating to the backend.
     * 
     * @param ratingData The rating data to submit
     * @return True if the submission was successful, false otherwise
     */
    suspend fun submitRating(ratingData: RatingDialogHelper.RatingData): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // In a real app, this would make an HTTP request to the server
                // For now, we'll just simulate a successful submission
                simulateNetworkCall()
                true
            } catch (e: Exception) {
                // Log the error
                e.printStackTrace()
                false
            }
        }
    }
    
    private suspend fun simulateNetworkCall() {
        // Simulate network delay
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
    }
} 