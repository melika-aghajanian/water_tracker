package com.example.water_reminder.ui.screens.settings

/**
 * Data class representing the state of settings in the Settings screen.
 *
 * @param userName The user's name.
 * @param workOut The user's workout duration.
 * @param weight The user's weight.
 * @param height The user's height.
 * @param wakeUpTime The time when the user wakes up.
 * @param sleepTime The time when the user goes to sleep.
 * @param dailyGoals The user's daily water intake goal.
 * @param reminderInterval The interval for water intake reminders.
 */
data class SettingsState(
    val userName: String = "",
    val workOut: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val wakeUpTime: String = "",
    val sleepTime: String = "",
    val dailyGoals: Int = 0,
    val reminderInterval: Int = 0
)

