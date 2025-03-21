package com.ramlekhak.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.AppDatabase
import com.ramlekhak.data.DailyCount
import com.ramlekhak.data.EntryRepository
import com.ramlekhak.data.MonthlyCount
import kotlinx.coroutines.launch

/**
 * ViewModel for the Statistics screen.
 */
class StatisticsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EntryRepository
    
    val dailyStats: LiveData<List<DailyCount>>
    val monthlyStats: LiveData<List<MonthlyCount>>
    val totalCount: LiveData<Int?>
    
    private val _resetEvent = MutableLiveData<ResetEvent>()
    val resetEvent: LiveData<ResetEvent> = _resetEvent

    init {
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        
        dailyStats = repository.getLastWeekDailyCounts()
        monthlyStats = repository.getMonthlyCounts()
        totalCount = repository.totalCount
    }

    /**
     * Reset all statistics
     */
    fun resetStatistics() {
        viewModelScope.launch {
            try {
                repository.resetAllStatistics()
                _resetEvent.postValue(ResetEvent.Success)
            } catch (e: Exception) {
                _resetEvent.postValue(ResetEvent.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

    /**
     * Reset event sealed class
     */
    sealed class ResetEvent {
        object Success : ResetEvent()
        data class Error(val message: String) : ResetEvent()
    }
}
