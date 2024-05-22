package com.example.water_reminder.ui.screens.settings

//data class SettingsState(
//    val dailyGoals: Int = SharedPrefHelper.DEFAULT_DAILY_GOAL
//)

data class SettingsState(
    val userName: String = "",
    val workOut: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val wakeUpTime: String = "",
    val sleepTime: String = "",
    val dailyGoals: Int = 2700,
    val reminderInterval: Int = 30
)
