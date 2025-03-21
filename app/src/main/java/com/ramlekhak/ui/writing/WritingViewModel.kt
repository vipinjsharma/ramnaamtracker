package com.ramlekhak.ui.writing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.AppDatabase
import com.ramlekhak.data.EntryRepository
import kotlinx.coroutines.launch

class WritingViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: EntryRepository
    
    // Current count of Ram written on the board
    private val _currentCount = MutableLiveData<Int>(0)
    val currentCount: LiveData<Int> = _currentCount
    
    // Total count for today
    val todayCount: LiveData<Int?>
    
    // Total count overall
    val totalCount: LiveData<Int?>
    
    // Mala count (1 mala = 108 repetitions)
    private val _currentMalaCount = MutableLiveData<Int>(0)
    val currentMalaCount: LiveData<Int> = _currentMalaCount
    
    init {
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
        todayCount = repository.getTodayCount()
        totalCount = repository.totalCount
    }
    
    /**
     * Increment the count when "राम" is written
     */
    fun incrementCount() {
        val count = _currentCount.value ?: 0
        _currentCount.value = count + 1
        updateMalaCount()
    }
    
    /**
     * Reset the current count
     */
    fun resetCurrentCount() {
        _currentCount.value = 0
        _currentMalaCount.value = 0
    }
    
    /**
     * Submit the current count to the database
     */
    fun submitCount() {
        val count = _currentCount.value ?: 0
        if (count > 0) {
            viewModelScope.launch {
                repository.insert(count)
                resetCurrentCount()
            }
        }
    }
    
    /**
     * Update the mala count based on current count
     * 1 mala = 108 repetitions
     */
    private fun updateMalaCount() {
        val count = _currentCount.value ?: 0
        _currentMalaCount.value = count / 108
    }
}