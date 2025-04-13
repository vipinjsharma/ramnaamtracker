package com.ramlekhak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.Count
import com.ramlekhak.data.CountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

/**
 * ViewModel for the Writing screen.
 */
@HiltViewModel
class WritingViewModel @Inject constructor(
    private val repository: CountRepository
) : ViewModel() {

    private val _todayCount = MutableLiveData(0)
    val todayCount: LiveData<Int> = _todayCount

    init {
        loadTodayCount()
    }

    private fun loadTodayCount() {
        viewModelScope.launch {
            repository.getTodayCount().collect { counts ->
                _todayCount.value = counts.sumOf { it.count }
            }
        }
    }

    fun addCount(count: Int) {
        viewModelScope.launch {
            repository.insert(Count(date = Date(), count = count))
        }
    }
}
