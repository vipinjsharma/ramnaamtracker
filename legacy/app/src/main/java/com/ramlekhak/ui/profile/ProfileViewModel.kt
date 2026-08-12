package com.ramlekhak.ui.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.CountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: CountRepository,
    @Named("app_preferences") private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _totalCount = MutableLiveData<Int>()
    val totalCount: LiveData<Int> = _totalCount

    private val _totalMalas = MutableLiveData<Int>()
    val totalMalas: LiveData<Int> = _totalMalas

    private val _currentStreak = MutableLiveData<Int>()
    val currentStreak: LiveData<Int> = _currentStreak

    private val _longestStreak = MutableLiveData<Int>()
    val longestStreak: LiveData<Int> = _longestStreak

    private val _dailyGoal = MutableLiveData<Int>()
    val dailyGoal: LiveData<Int> = _dailyGoal

    private val _monthlyGoal = MutableLiveData<Int>()
    val monthlyGoal: LiveData<Int> = _monthlyGoal

    private val _todayCount = MutableLiveData<Int>()
    val todayCount: LiveData<Int> = _todayCount

    private val _monthCount = MutableLiveData<Int>()
    val monthCount: LiveData<Int> = _monthCount

    init {
        loadGoals()
        loadUserStatistics()
    }

    private fun loadUserStatistics() {
        viewModelScope.launch {
            loadTotalCount()
            loadTodayCount()
            loadMonthlyCount()
            loadStreakCounts()
        }
    }
    
    private fun loadTotalCount() {
        viewModelScope.launch {
            val totalMalas = repository.getTotalMalas()
            _totalMalas.postValue(totalMalas)
            _totalCount.postValue(totalMalas * 108)
        }
    }
    
    private fun loadTodayCount() {
        viewModelScope.launch {
            repository.getTodayCount().collect { counts ->
                val todayTotal = counts.sumOf { it.count }
                _todayCount.postValue(todayTotal)
            }
        }
    }
    
    private fun loadMonthlyCount() {
        viewModelScope.launch {
            val monthlyCount = repository.getMonthlyProgress()
            _monthCount.postValue(monthlyCount)
        }
    }
    
    private fun loadStreakCounts() {
        viewModelScope.launch {
            val streak = repository.getCurrentStreak()
            _currentStreak.postValue(streak)
            
            val longestStreak = repository.getLongestStreak()
            _longestStreak.postValue(longestStreak)
        }
    }

    private fun getDateDaysAgo(days: Int): Date {
        return Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -days)
        }.time
    }

    private fun loadGoals() {
        _dailyGoal.value = sharedPreferences.getInt(PREF_DAILY_GOAL, 108)
        _monthlyGoal.value = sharedPreferences.getInt(PREF_MONTHLY_GOAL, 3240)
    }

    fun saveGoals(dailyGoal: Int, monthlyGoal: Int) {
        sharedPreferences.edit()
            .putInt(PREF_DAILY_GOAL, dailyGoal)
            .putInt(PREF_MONTHLY_GOAL, monthlyGoal)
            .apply()

        _dailyGoal.value = dailyGoal
        _monthlyGoal.value = monthlyGoal
    }

    fun getUserName(): String {
        return sharedPreferences.getString(PREF_USER_NAME, "Ram Bhakt") ?: "Ram Bhakt"
    }

    fun saveUserName(name: String) {
        sharedPreferences.edit()
            .putString(PREF_USER_NAME, name)
            .apply()
    }

    fun getReminderSettings(): ReminderSettings {
        val enabled = sharedPreferences.getBoolean(PREF_REMINDER_ENABLED, false)
        val time = sharedPreferences.getString(PREF_REMINDER_TIME, "7:00 AM") ?: "7:00 AM"
        val days = sharedPreferences.getString(PREF_REMINDER_DAYS, "")
            ?.split(",")
            ?.filter { it.isNotEmpty() }
            ?.map { it.toInt() }
            ?: listOf()

        return ReminderSettings(enabled, time, days)
    }

    fun saveReminderSettings(settings: ReminderSettings) {
        sharedPreferences.edit()
            .putBoolean(PREF_REMINDER_ENABLED, settings.enabled)
            .putString(PREF_REMINDER_TIME, settings.time)
            .putString(PREF_REMINDER_DAYS, settings.days.joinToString(","))
            .apply()
    }

    companion object {
        private const val PREF_USER_NAME = "user_name"
        private const val PREF_DAILY_GOAL = "daily_goal"
        private const val PREF_MONTHLY_GOAL = "monthly_goal"
        private const val PREF_REMINDER_ENABLED = "reminder_enabled"
        private const val PREF_REMINDER_TIME = "reminder_time"
        private const val PREF_REMINDER_DAYS = "reminder_days"
    }
}

data class ReminderSettings(
    val enabled: Boolean,
    val time: String,
    val days: List<Int>
) 