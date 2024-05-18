package com.andriawan.hydrationtracker.ui.screens.settings

import com.andriawan.hydrationtracker.utils.SharedPrefHelper

//data class SettingsState(
//    val dailyGoals: Int = SharedPrefHelper.DEFAULT_DAILY_GOAL
//)

data class SettingsState(
    val userName: String = "",
    val dailyGoals: Int = 2700,
    val weight: Int = 0,
    val height: Int = 0,
    val wakeUpTime: String = "",
    val sleepTime: String = ""
)
