package com.ramlekhak.data.repository

import com.ramlekhak.data.models.Language
import com.ramlekhak.data.models.Statistics
import com.ramlekhak.data.models.Theme
import com.ramlekhak.data.models.User
import kotlinx.coroutines.flow.Flow

interface RamLekhakRepository {
    // User operations
    suspend fun getUser(userId: String): User?
    suspend fun createUser(user: User)
    suspend fun updateUserName(userId: String, name: String)
    suspend fun updateUserTheme(userId: String, theme: Theme)
    suspend fun updateUserLanguage(userId: String, language: Language)
    suspend fun updateUserGoals(userId: String, dailyGoal: Int, monthlyGoal: Int)
    
    // Statistics operations
    fun getStatistics(userId: String): Flow<Statistics>
    suspend fun incrementCount(userId: String)
    suspend fun resetDailyCounts(userId: String)
    suspend fun updateStreak(userId: String)
    
    // Settings operations
    suspend fun setDailyReminder(userId: String, enabled: Boolean, hour: Int, minute: Int)
    suspend fun getDailyReminderSettings(userId: String): Triple<Boolean, Int, Int>
    
    // Preferences
    suspend fun getSelectedTheme(userId: String): Theme
    suspend fun getSelectedLanguage(userId: String): Language
} 