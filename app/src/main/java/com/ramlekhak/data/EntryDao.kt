package com.ramlekhak.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.Date

/**
 * Data Access Object for the Entry entity.
 */
@Dao
interface EntryDao {
    
    /**
     * Insert a new entry into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry): Long
    
    /**
     * Get all entries, ordered by date descending
     */
    @Query("SELECT * FROM entries ORDER BY date DESC")
    fun getAllEntries(): LiveData<List<Entry>>
    
    /**
     * Get entries for a specific date
     */
    @Query("SELECT * FROM entries WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getEntriesByDateRange(startDate: Date, endDate: Date): LiveData<List<Entry>>
    
    /**
     * Get the total count for a specific date
     */
    @Query("SELECT SUM(count) FROM entries WHERE date BETWEEN :startDate AND :endDate")
    fun getTotalCountForDateRange(startDate: Date, endDate: Date): LiveData<Int?>
    
    /**
     * Get the total count of all entries
     */
    @Query("SELECT SUM(count) FROM entries")
    fun getTotalCount(): LiveData<Int?>
    
    /**
     * Delete all entries
     */
    @Query("DELETE FROM entries")
    suspend fun deleteAllEntries()
    
    /**
     * Delete entries before a specific date
     */
    @Query("DELETE FROM entries WHERE date < :date")
    suspend fun deleteEntriesBeforeDate(date: Date)
    
    /**
     * Get daily counts for the last week
     */
    @Query("SELECT date, SUM(count) as count FROM entries " +
           "WHERE date BETWEEN :startDate AND :endDate " +
           "GROUP BY strftime('%Y-%m-%d', date/1000, 'unixepoch') " +
           "ORDER BY date ASC")
    fun getDailyCounts(startDate: Date, endDate: Date): LiveData<List<DailyCount>>
    
    /**
     * Get monthly counts
     */
    @Query("SELECT strftime('%Y-%m', date/1000, 'unixepoch') as month, SUM(count) as count " +
           "FROM entries " +
           "GROUP BY month " +
           "ORDER BY month ASC")
    fun getMonthlyCounts(): LiveData<List<MonthlyCount>>
}

/**
 * Data class to represent daily counts
 */
data class DailyCount(
    val date: Date,
    val count: Int
)

/**
 * Data class to represent monthly counts
 */
data class MonthlyCount(
    val month: String,
    val count: Int
)
