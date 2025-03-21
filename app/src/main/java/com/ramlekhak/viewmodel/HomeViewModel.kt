package com.ramlekhak.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.AppDatabase
import com.ramlekhak.data.EntryRepository
import com.ramlekhak.data.UserPreferences
import com.ramlekhak.utils.DateUtils
import kotlinx.coroutines.launch
import java.util.Date

/**
 * ViewModel for the Home screen.
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EntryRepository
    private val userPreferences: UserPreferences
    
    val todayCount: LiveData<Int?>
    val totalCount: LiveData<Int?>
    val malaCount: LiveData<Int>

    init {
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        userPreferences = UserPreferences(application)
        
        // Check if we need to reset today's count
        checkAndResetDailyCount()
        
        todayCount = repository.getTodayCount()
        totalCount = repository.totalCount
        
        // Calculate mala count (1 mala = 108 repetitions)
        malaCount = totalCount.map { total ->
            (total ?: 0) / 108
        }
    }

    /**
     * Check if we need to reset the daily count (if enabled in preferences)
     */
    private fun checkAndResetDailyCount() {
        val resetEnabled = userPreferences.isResetAtMidnightEnabled()
        if (resetEnabled) {
            val lastResetTimestamp = userPreferences.getLastResetDate()
            if (DateUtils.isNewDay(lastResetTimestamp)) {
                viewModelScope.launch {
                    repository.resetTodayCount()
                    userPreferences.setLastResetDate(Date().time)
                }
            }
        }
    }
}
