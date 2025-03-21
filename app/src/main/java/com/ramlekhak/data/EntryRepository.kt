package com.ramlekhak.data

import androidx.lifecycle.LiveData
import com.ramlekhak.utils.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

/**
 * Repository for managing Entry data.
 */
class EntryRepository(private val entryDao: EntryDao) {

    /**
     * Get all entries in the database
     */
    val allEntries: LiveData<List<Entry>> = entryDao.getAllEntries()

    /**
     * Get total count of all entries
     */
    val totalCount: LiveData<Int?> = entryDao.getTotalCount()

    /**
     * Get entries for today
     */
    fun getTodayEntries(): LiveData<List<Entry>> {
        val todayStart = DateUtils.getStartOfDay(Date())
        val todayEnd = DateUtils.getEndOfDay(Date())
        return entryDao.getEntriesByDateRange(todayStart, todayEnd)
    }

    /**
     * Get today's total count
     */
    fun getTodayCount(): LiveData<Int?> {
        val todayStart = DateUtils.getStartOfDay(Date())
        val todayEnd = DateUtils.getEndOfDay(Date())
        return entryDao.getTotalCountForDateRange(todayStart, todayEnd)
    }

    /**
     * Insert a new entry
     */
    suspend fun insert(count: Int, note: String? = null) = withContext(Dispatchers.IO) {
        val entry = Entry(date = Date(), count = count, note = note)
        entryDao.insert(entry)
    }

    /**
     * Reset all statistics
     */
    suspend fun resetAllStatistics() = withContext(Dispatchers.IO) {
        entryDao.deleteAllEntries()
    }

    /**
     * Reset today's count (used when midnight reset is enabled)
     */
    suspend fun resetTodayCount() = withContext(Dispatchers.IO) {
        val yesterday = DateUtils.getYesterday()
        entryDao.deleteEntriesBeforeDate(yesterday)
    }

    /**
     * Get daily counts for the last week
     */
    fun getLastWeekDailyCounts(): LiveData<List<DailyCount>> {
        val endDate = DateUtils.getEndOfDay(Date())
        val startDate = DateUtils.getStartOfDay(DateUtils.addDays(Date(), -6))
        return entryDao.getDailyCounts(startDate, endDate)
    }

    /**
     * Get monthly counts
     */
    fun getMonthlyCounts(): LiveData<List<MonthlyCount>> {
        return entryDao.getMonthlyCounts()
    }
}
