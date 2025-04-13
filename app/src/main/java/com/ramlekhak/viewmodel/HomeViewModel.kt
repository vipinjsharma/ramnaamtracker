package com.ramlekhak.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.Count
import com.ramlekhak.data.CountRepository
import com.ramlekhak.data.UserPreferences
import com.ramlekhak.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date

/**
 * ViewModel for the Home screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: CountRepository,
    private val userPreferences: UserPreferences
) : AndroidViewModel(application) {

    private val _streakCount = MutableLiveData(0)
    val streakCount: LiveData<Int> = _streakCount

    private val _todayMalas = MutableLiveData(0)
    val todayMalas: LiveData<Int> = _todayMalas

    private val _todayCount = MutableLiveData(0)
    val todayCount: LiveData<Int> = _todayCount

    private val _dailyProgress = MutableLiveData(0)
    val dailyProgress: LiveData<Int> = _dailyProgress

    private val _dailyGoal = MutableLiveData(108) // Default 1 mala
    val dailyGoal: LiveData<Int> = _dailyGoal

    private val _monthlyGoal = MutableLiveData(3240) // Default 30 malas
    val monthlyGoal: LiveData<Int> = _monthlyGoal

    init {
        // Check if we need to reset today's count
        checkAndResetDailyCount()
        
        // Load initial data
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            repository.getTodayCount().collect { counts ->
                val totalCount = counts.sumOf { it.count }
                _todayCount.value = totalCount
                _todayMalas.value = totalCount / 108
                updateProgress()
            }
        }
    }

    private fun updateProgress() {
        val progress = ((_todayCount.value ?: 0).toFloat() / _dailyGoal.value!!.toFloat() * 100).toInt()
        _dailyProgress.value = progress.coerceIn(0, 100)
    }

    private fun checkAndResetDailyCount() {
        val resetEnabled = userPreferences.isResetAtMidnightEnabled()
        if (resetEnabled) {
            val lastResetTimestamp = userPreferences.getLastResetDate()
            if (DateUtils.isNewDay(lastResetTimestamp)) {
                viewModelScope.launch {
                    repository.deleteAll()
                    userPreferences.setLastResetDate(Date().time)
                    loadInitialData()
                }
            }
        }
    }

    fun submitDrawing(bitmap: Bitmap?) {
        if (bitmap == null) return
        
        viewModelScope.launch {
            // Save the drawing
            repository.insert(Count(date = Date(), count = 1))
            
            // Increment counts
            _todayCount.value = (_todayCount.value ?: 0) + 1
            _todayMalas.value = _todayCount.value!! / 108
            
            // Update progress
            updateProgress()
        }
    }

    fun shareProgress() {
        // TODO: Implement sharing functionality
    }

    fun setDailyGoal(count: Int) {
        _dailyGoal.value = count
        updateProgress()
    }

    fun setMonthlyGoal(count: Int) {
        _monthlyGoal.value = count
    }

    fun resetCounts() {
        viewModelScope.launch {
            repository.deleteAll()
            loadInitialData()
        }
    }

    fun addCount(count: Int) {
        viewModelScope.launch {
            repository.insert(Count(date = Date(), count = count))
        }
    }

    fun resetStatistics() {
        viewModelScope.launch {
            repository.deleteAll()
            loadInitialData()
        }
    }
}
