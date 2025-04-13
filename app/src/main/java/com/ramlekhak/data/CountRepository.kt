package com.ramlekhak.data

import com.ramlekhak.data.dao.CountDao
import com.ramlekhak.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountRepository @Inject constructor(
    private val countDao: CountDao
) {
    fun getAllCounts(): Flow<List<Count>> = countDao.getAllCounts()

    suspend fun insert(count: Count) = countDao.insert(count)

    suspend fun delete(count: Count) = countDao.delete(count)

    suspend fun deleteAll() = countDao.deleteAll()

    fun getTodayCount(): Flow<List<Count>> {
        val today = Date()
        val startOfDay = DateUtils.getStartOfDay(today)
        val endOfDay = DateUtils.getEndOfDay(today)
        return countDao.getTodayCount(startOfDay, endOfDay)
    }
    
    /**
     * Get the count entry for a specific date
     */
    suspend fun getCountForDate(date: Date): Count? {
        return countDao.getCountForDate(date)
    }
    
    /**
     * Insert a new count or update an existing one for the same date
     */
    suspend fun insertOrUpdateCount(count: Count) {
        val existingCount = countDao.getCountForDate(count.date)
        if (existingCount != null) {
            // Update existing count with same ID
            countDao.update(count.copy(id = existingCount.id))
        } else {
            // Insert new count
            countDao.insert(count)
        }
    }
    
    /**
     * Get the streak count (number of consecutive days with entries) since a given date
     */
    suspend fun getStreakCount(startDate: Date): Int {
        return countDao.getStreakCount(startDate) ?: 0
    }

    suspend fun getTotalMalas(): Int {
        val totalCount = countDao.getTotalCount() ?: 0
        return totalCount / 108
    }

    suspend fun getCurrentStreak(): Int {
        val counts = countDao.getAllCountsList()
        var streak = 0
        val calendar = Calendar.getInstance()
        
        for (i in counts.size - 1 downTo 0) {
            val count = counts[i]
            val countDate = Calendar.getInstance().apply { time = count.date }
            
            if (isConsecutiveDay(calendar, countDate)) {
                streak++
                calendar.time = countDate.time
            } else {
                break
            }
        }
        
        return streak
    }

    suspend fun getLongestStreak(): Int {
        val counts = countDao.getAllCountsList()
        var currentStreak = 0
        var longestStreak = 0
        val calendar = Calendar.getInstance()
        
        for (i in counts.size - 1 downTo 0) {
            val count = counts[i]
            val countDate = Calendar.getInstance().apply { time = count.date }
            
            if (isConsecutiveDay(calendar, countDate)) {
                currentStreak++
                longestStreak = maxOf(longestStreak, currentStreak)
                calendar.time = countDate.time
            } else {
                currentStreak = 1
                calendar.time = countDate.time
            }
        }
        
        return longestStreak
    }

    suspend fun getMonthlyProgress(): Int {
        val calendar = Calendar.getInstance()
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        
        return countDao.getCountsForMonth(currentYear, currentMonth).sumOf { it.count }
    }

    suspend fun getYearlyProgress(): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        
        return countDao.getCountsForYear(currentYear).sumOf { it.count }
    }

    suspend fun resetStatistics() {
        countDao.deleteAll()
    }

    private fun isConsecutiveDay(current: Calendar, previous: Calendar): Boolean {
        val diffInMillis = current.timeInMillis - previous.timeInMillis
        val diffInDays = diffInMillis / (24 * 60 * 60 * 1000)
        return diffInDays <= 1
    }
} 