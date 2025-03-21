package com.ramlekhak.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Utility class for date operations.
 */
object DateUtils {
    
    private val calendar = Calendar.getInstance()
    
    /**
     * Get the start of the current day (midnight)
     */
    fun getStartOfDay(date: Date): Date {
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
        return addDays(Date(), -1)
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
    fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.time = date1
        cal2.time = date2
        
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
}

/**
 * Room TypeConverter for Date objects
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
