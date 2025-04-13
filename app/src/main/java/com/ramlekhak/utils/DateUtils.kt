package com.ramlekhak.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.ramlekhak.data.Count

/**
 * Utility class for date operations.
 */
object DateUtils {
    
    private val calendar = Calendar.getInstance()
    
    /**
     * Get the start of the current day (midnight)
     */
    fun getStartOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
    
    /**
     * Get the end of the current day (23:59:59.999)
     */
    fun getEndOfDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
    
    /**
     * Get yesterday's date
     */
    fun getYesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return calendar.time
    }
    
    /**
     * Get the previous day
     */
    fun getPreviousDay(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return calendar.time
    }
    
    /**
     * Add days to a date
     */
    fun addDays(date: Date, days: Int): Date {
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, days)
        return calendar.time
    }
    
    /**
     * Check if two dates are the same day
     */
    fun isSameDay(date1: Date?, date2: Date?): Boolean {
        if (date1 == null || date2 == null) return false
        
        val cal1 = Calendar.getInstance().apply { time = date1 }
        val cal2 = Calendar.getInstance().apply { time = date2 }
        
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }
    
    /**
     * Format date as a readable string
     */
    fun formatDate(date: Date, pattern: String = "dd MMM yyyy"): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(date)
    }
    
    /**
     * Check if it's a new day since the last recorded timestamp
     */
    fun isNewDay(lastTimestamp: Long): Boolean {
        if (lastTimestamp == 0L) return true
        
        val lastDate = Date(lastTimestamp)
        val currentDate = Date()
        
        return !isSameDay(lastDate, currentDate)
    }

    fun getMonthlyProgress(counts: List<Count>): List<Int> {
        val calendar = Calendar.getInstance()
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val monthlyProgress: MutableList<Int> = MutableList(daysInMonth) { 0 }
        
        counts.forEach { count ->
            val countCalendar = Calendar.getInstance().apply { time = count.date }
            val dayOfMonth = countCalendar.get(Calendar.DAY_OF_MONTH) - 1
            if (dayOfMonth < daysInMonth) {
                monthlyProgress[dayOfMonth] += count.count
            }
        }
        
        return monthlyProgress
    }

    fun getStartOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        return getStartOfDay(calendar.time)
    }
    
    fun getEndOfWeek(date: Date): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        return getEndOfDay(calendar.time)
    }
    
    fun getStartOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return getStartOfDay(calendar.time)
    }
    
    fun getEndOfMonth(date: Date): Date {
        val calendar = Calendar.getInstance().apply { time = date }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        return getEndOfDay(calendar.time)
    }
    
    fun shouldResetCount(lastDate: Date?, currentDate: Date): Boolean {
        return !isSameDay(lastDate, currentDate)
    }
    
    /**
     * Get a date that is a specified number of days before the given date
     */
    fun getDateMinusDays(date: Date, days: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return calendar.time
    }
}
