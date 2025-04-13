package com.ramlekhak.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "counts")
data class Count(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Date,
    val count: Int
) 
