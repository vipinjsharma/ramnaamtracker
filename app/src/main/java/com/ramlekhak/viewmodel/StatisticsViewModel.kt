package com.ramlekhak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.CountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Statistics screen.
 */
@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val repository: CountRepository
) : ViewModel() {

    private val _totalMalas = MutableLiveData<Int>()
    val totalMalas: LiveData<Int> = _totalMalas

    private val _currentStreak = MutableLiveData<Int>()
    val currentStreak: LiveData<Int> = _currentStreak

    private val _longestStreak = MutableLiveData<Int>()
    val longestStreak: LiveData<Int> = _longestStreak

    private val _monthlyProgress = MutableLiveData<Int>()
    val monthlyProgress: LiveData<Int> = _monthlyProgress

    private val _yearlyProgress = MutableLiveData<Int>()
    val yearlyProgress: LiveData<Int> = _yearlyProgress

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            _totalMalas.value = repository.getTotalMalas()
            _currentStreak.value = repository.getCurrentStreak()
            _longestStreak.value = repository.getLongestStreak()
            _monthlyProgress.value = repository.getMonthlyProgress()
            _yearlyProgress.value = repository.getYearlyProgress()
        }
    }

    fun resetStatistics() {
        viewModelScope.launch {
            repository.resetStatistics()
            loadStatistics()
        }
    }
}
