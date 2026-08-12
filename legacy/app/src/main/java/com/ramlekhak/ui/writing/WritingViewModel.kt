package com.ramlekhak.ui.writing

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.Count
import com.ramlekhak.data.CountRepository
import com.ramlekhak.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.Calendar
import javax.inject.Inject
import android.content.SharedPreferences
import android.preference.PreferenceManager

@HiltViewModel
class WritingViewModel @Inject constructor(
    private val repository: CountRepository
) : ViewModel() {
    
    private val _todayCount = MutableLiveData<Int>(0)
    val todayCount: LiveData<Int> = _todayCount
    
    private val _streakCount = MutableLiveData<Int>(0)
    val streakCount: LiveData<Int> = _streakCount
    
    private val _todayMalas = MutableLiveData<Int>(0)
    val todayMalas: LiveData<Int> = _todayMalas
    
    private val _dailyProgress = MutableLiveData<Int>(0)
    val dailyProgress: LiveData<Int> = _dailyProgress
    
    private val _dailyGoal = MutableLiveData<Int>(108) // Default goal is 108
    val dailyGoal: LiveData<Int> = _dailyGoal
    
    private val _drawing = MutableLiveData<Bitmap?>(null)
    val drawing: LiveData<Bitmap?> = _drawing
    
    private val malaSize = 108 // Number of counts in one mala

    init {
        loadDailyGoal()
        loadTodayCount()
        loadStreakCount()
    }

    private fun loadDailyGoal() {
        // In a real app, you would load this from SharedPreferences or database
        // For now, we'll use the default value set in the MutableLiveData
    }
    
    fun setDailyGoal(goal: Int) {
        _dailyGoal.value = goal
        // In a real app, you would save this to SharedPreferences or database
        
        // Recalculate progress with the new goal
        _todayCount.value?.let { updateMalasAndProgress(it) }
    }

    private fun loadTodayCount() {
        viewModelScope.launch {
            val today = getTodayDate()
            val startOfDay = DateUtils.getStartOfDay(today)
            val endOfDay = DateUtils.getEndOfDay(today)
            
            repository.getTodayCount().collect { counts ->
                val totalCount = counts.sumOf { it.count }
                _todayCount.value = totalCount
                updateMalasAndProgress(totalCount)
            }
        }
    }
    
    private fun loadStreakCount() {
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, -1) // Look back one month
            val oneMonthAgo = calendar.time
            
            val streak = repository.getStreakCount(oneMonthAgo)
            _streakCount.value = streak
        }
    }
    
    private fun updateMalasAndProgress(count: Int) {
        // Calculate malas (108 counts = 1 mala)
        val malas = count / malaSize
        _todayMalas.value = malas
        
        // Calculate progress percentage toward daily goal
        val goal = _dailyGoal.value ?: 108
        val progress = (count * 100) / goal
        _dailyProgress.value = minOf(progress, 100) // Cap at 100%
    }

    fun submitDrawing(drawing: Bitmap) {
        viewModelScope.launch {
            // Increment count by 1 (one RAM naam written)
            val currentCount = _todayCount.value ?: 0
            val newCount = currentCount + 1
            
            // Save the drawing (in a real app, you might save it to storage)
            // This uses the parameter properly to avoid the warning
            _drawing.value = drawing
            
            // Update repository
            saveCount(newCount)
            
            // Update UI
            _todayCount.value = newCount
            updateMalasAndProgress(newCount)
            
            // Also refresh streak count
            loadStreakCount()
        }
    }
    
    private suspend fun saveCount(count: Int) {
        withContext(Dispatchers.IO) {
            val today = getTodayDate()
            repository.insertOrUpdateCount(Count(date = today, count = count))
        }
    }
    
    /**
     * Generate a bitmap with RAM written in Devanagari
     */
    fun generateRamDrawing(): Bitmap {
        // Create a bitmap with black background
        val bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.parseColor("#252525")) // Match the DrawingView background
        
        // Paint for RAM text
        val paint = Paint().apply {
            color = Color.WHITE
            textSize = 300f
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = 10f
            textAlign = Paint.Align.CENTER
            typeface = android.graphics.Typeface.DEFAULT_BOLD
        }
        
        // Draw RAM in Devanagari at the center
        canvas.drawText("राम", 400f, 400f, paint)
        
        return bitmap
    }
    
    private fun getTodayDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
}