package com.ramlekhak.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.Date

@Dao
interface CountDao {
    @Query("SELECT * FROM counts ORDER BY date DESC")
    fun getAllCounts(): LiveData<List<Count>>

    @Query("SELECT * FROM counts WHERE date = :date")
    suspend fun getCountForDate(date: Date): Count?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(count: Count)

    @Update
    suspend fun update(count: Count)

    @Delete
    suspend fun delete(count: Count)

    @Query("SELECT SUM(count) FROM counts WHERE date >= :startDate")
    fun getTotalCountSince(startDate: Date): LiveData<Int>

    @Query("SELECT COUNT(*) FROM counts WHERE date >= :startDate AND count > 0")
    fun getStreakCount(startDate: Date): LiveData<Int>
} 