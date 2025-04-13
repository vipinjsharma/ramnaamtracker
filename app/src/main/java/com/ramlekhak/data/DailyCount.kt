package com.ramlekhak.data

import androidx.room.DatabaseView
import java.util.Date

/**
 * Database view for daily count aggregation
 */
@DatabaseView(
    """
    SELECT 
        date,
        COALESCE(SUM(count), 0) as total
    FROM counts 
    GROUP BY strftime('%Y-%m-%d', date/1000, 'unixepoch')
    ORDER BY date ASC
    """
)
data class DailyCount(
    val date: Date,
    val total: Int
) 