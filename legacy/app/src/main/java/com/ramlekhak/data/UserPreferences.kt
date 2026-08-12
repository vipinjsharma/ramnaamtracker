package com.ramlekhak.data

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Manages user preferences using SharedPreferences.
 */
@Singleton
class UserPreferences @Inject constructor(
    @Named("default_preferences") private val prefs: SharedPreferences
) {
    
    companion object {
        private const val KEY_DARK_THEME = "dark_theme"
        private const val KEY_LANGUAGE = "language"
        private const val KEY_NOTIFICATIONS = "notifications"
        private const val KEY_RESET_AT_MIDNIGHT = "reset_at_midnight"
        private const val KEY_LAST_RESET_DATE = "last_reset_date"
        private const val PREFS_NAME = "ram_naam_prefs"
    }

    /**
     * Check if dark theme is enabled
     */
    fun isDarkThemeEnabled(): Boolean {
        return prefs.getBoolean(KEY_DARK_THEME, false)
    }

    /**
     * Set dark theme enabled/disabled
     * @param enabled Whether dark theme should be enabled
     * @param applyImmediately Whether to apply the theme change immediately
     */
    fun setDarkThemeEnabled(enabled: Boolean, applyImmediately: Boolean = false) {
        prefs.edit {
            putBoolean(KEY_DARK_THEME, enabled)
        }
        
        // Apply the theme change if requested
        if (applyImmediately) {
            if (enabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
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
        return prefs.getBoolean(KEY_RESET_AT_MIDNIGHT, false)
    }

    /**
     * Set reset at midnight enabled/disabled
     */
    fun setResetAtMidnight(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_RESET_AT_MIDNIGHT, enabled).apply()
    }

    /**
     * Get the date of the last count reset
     */
    fun getLastResetDate(): Long {
        return prefs.getLong(KEY_LAST_RESET_DATE, 0L)
    }

    /**
     * Set the date of the last count reset
     */
    fun setLastResetDate(timestamp: Long) {
        prefs.edit().putLong(KEY_LAST_RESET_DATE, timestamp).apply()
    }
}
