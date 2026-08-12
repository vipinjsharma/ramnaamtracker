package com.ramlekhak.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ramlekhak.data.Count
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface CountDao {
    @Query("SELECT * FROM counts ORDER BY date DESC")
    fun getAllCounts(): Flow<List<Count>>

    @Query("SELECT * FROM counts ORDER BY date DESC")
    suspend fun getAllCountsList(): List<Count>

    @Query("SELECT SUM(count) FROM counts")
    suspend fun getTotalCount(): Int?

    @Query("SELECT * FROM counts WHERE strftime('%Y-%m', date/1000, 'unixepoch') = :year || '-' || :month")
    suspend fun getCountsForMonth(year: Int, month: Int): List<Count>

    @Query("SELECT * FROM counts WHERE strftime('%Y', date/1000, 'unixepoch') = :year")
    suspend fun getCountsForYear(year: Int): List<Count>

    @Query("SELECT * FROM counts WHERE date >= :startOfDay AND date < :endOfDay")
    fun getTodayCount(startOfDay: Date, endOfDay: Date): Flow<List<Count>>
    
    @Query("SELECT * FROM counts WHERE date = :date LIMIT 1")
    suspend fun getCountForDate(date: Date): Count?
    
    @Update
    suspend fun update(count: Count)
    
    @Query("SELECT COUNT(*) FROM counts WHERE date >= :startDate AND count > 0")
    suspend fun getStreakCount(startDate: Date): Int?
    
    @Query("SELECT SUM(count) FROM counts WHERE date >= :startDate")
    suspend fun getTotalCountSince(startDate: Date): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(count: Count)

    @Delete
    suspend fun delete(count: Count)

    @Query("DELETE FROM counts")
    suspend fun deleteAll()
} 