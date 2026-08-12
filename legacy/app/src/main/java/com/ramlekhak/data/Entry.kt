package com.ramlekhak.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Entity representing a single Ram writing entry.
 */
@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    /**
     * The date when the entry was created
     */
    val date: Date,
    
    /**
     * The number of repetitions in this entry
     */
    val count: Int,
    
    /**
     * Optional note for this entry
     */
    val note: String? = null
)
