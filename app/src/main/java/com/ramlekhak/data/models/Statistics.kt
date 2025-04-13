package com.ramlekhak.data.models

data class Statistics(
    val userId: String,
    var todayCount: Int = 0,
    var totalCount: Int = 0,
    var currentStreak: Int = 0,
    var longestStreak: Int = 0,
    var todayMalas: Int = 0,
    var totalMalas: Int = 0,
    var dailyGoalProgress: Float = 0f,
    var monthlyGoalProgress: Float = 0f,
    var lastWrittenDate: Long? = null
) 