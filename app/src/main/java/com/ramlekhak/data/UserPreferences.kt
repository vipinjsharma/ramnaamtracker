package com.ramlekhak.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.util.*

/**
 * Manages user preferences using SharedPreferences.
 */
class UserPreferences(context: Context) {
    
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private const val KEY_DARK_THEME = "dark_theme"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_NOTIFICATIONS = "notifications"
        private const val KEY_RESET_AT_MIDNIGHT = "reset_at_midnight"
        private const val KEY_LAST_RESET_DATE = "last_reset_date"
    }

    /**
     * Check if dark theme is enabled
     */
    fun isDarkThemeEnabled(): Boolean {
        return prefs.getBoolean(KEY_DARK_THEME, false)
    }

    /**
     * Set dark theme enabled/disabled
     */
    fun setDarkThemeEnabled(enabled: Boolean) {
        prefs.edit {
            putBoolean(KEY_DARK_THEME, enabled)
        }
        
        // Apply the theme change
        if (enabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    /**
     * Get the app language
     */
    fun getLanguage(): String {
        return prefs.getString(KEY_LANGUAGE, Locale.getDefault().language) ?: "en"
    }

    /**
     * Set the app language
     */
    fun setLanguage(language: String) {
        prefs.edit {
            putString(KEY_LANGUAGE, language)
        }
    }

    /**
     * Check if notifications are enabled
     */
    fun areNotificationsEnabled(): Boolean {
        return prefs.getBoolean(KEY_NOTIFICATIONS, true)
    }

    /**
     * Set notifications enabled/disabled
     */
    fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit {
            putBoolean(KEY_NOTIFICATIONS, enabled)
        }
    }

    /**
     * Check if reset at midnight is enabled
     */
    fun isResetAtMidnightEnabled(): Boolean {
        return prefs.getBoolean(KEY_RESET_AT_MIDNIGHT, true)
    }

    /**
     * Set reset at midnight enabled/disabled
     */
    fun setResetAtMidnightEnabled(enabled: Boolean) {
        prefs.edit {
            putBoolean(KEY_RESET_AT_MIDNIGHT, enabled)
        }
    }

    /**
     * Get the date of the last count reset
     */
    fun getLastResetDate(): Long {
        return prefs.getLong(KEY_LAST_RESET_DATE, 0)
    }

    /**
     * Set the date of the last count reset
     */
    fun setLastResetDate(timestamp: Long) {
        prefs.edit {
            putLong(KEY_LAST_RESET_DATE, timestamp)
        }
    }
}
