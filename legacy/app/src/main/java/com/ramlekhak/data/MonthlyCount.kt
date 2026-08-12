package com.ramlekhak.data

import androidx.room.DatabaseView
import androidx.room.ColumnInfo

/**
 * Database view for monthly count aggregation
 */
@DatabaseView(
    """
    SELECT 
        strftime('%Y-%m', date/1000, 'unixepoch') as month,
        COALESCE(SUM(count), 0) as total
    FROM counts 
    GROUP BY month
    ORDER BY month ASC
    """
)
data class MonthlyCount(
    @ColumnInfo(name = "month")
    val month: String,
    
    @ColumnInfo(name = "total")
    val total: Int
) 