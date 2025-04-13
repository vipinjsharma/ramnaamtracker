package com.ramlekhak.data.models

import java.util.Date

data class User(
    val id: String,
    var name: String,
    val memberSince: Date,
    var dailyGoal: Int = 108, // Default 1 mala
    var monthlyGoal: Int = 3240, // Default 30 malas
    var selectedTheme: Theme = Theme.RAM,
    var selectedLanguage: Language = Language.ENGLISH
)

enum class Theme(val colorHex: String, val bgColorHex: String) {
    RAM("#ff7817", "#fff7e6"),
    KRISHNA("#2874A6", "#EBF5FB"),
    LAKSHMI("#FFD700", "#FFFAF0"),
    GANESH("#FF4500", "#FFF5EE"),
    SHIVA("#800080", "#F8F4FF")
}

enum class Language {
    ENGLISH,
    HINDI
} 