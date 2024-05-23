package com.example.water_reminder.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.water_reminder.utils.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(SettingsState())
        private set

    init {
        initData()
    }

    private fun initData() {
        val userName = SharedPrefHelper.readString(SharedPrefHelper.PREF_USER_NAME, "")
        val workOut = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WORK_OUT,0)
//        val dailyGoals = SharedPrefHelper.readInt(SharedPrefHelper.PREF_DAILY_GOAL, SharedPrefHelper.DEFAULT_DAILY_GOAL)
        val weight = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WEIGHT, 0)
        val height = SharedPrefHelper.readInt(SharedPrefHelper.PREF_HEIGHT, 0)
        val wakeUpTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_WAKE_UP_TIME, "")
        val sleepTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_SLEEP_TIME, "")

        val dailyGoals = calculateDailyGoals(weight, workOut)

        state = state.copy(
            userName = userName,
            workOut = workOut,
            weight = weight,
            height = height,
            wakeUpTime = wakeUpTime,
            sleepTime = sleepTime,
            dailyGoals = dailyGoals
        )
    }

    private fun calculateDailyGoals(weight: Int, workOut: Int): Int {
        return (weight * 100 / 3) + (workOut / 6 * 7)
    }

    fun saveNewUserName(newUserName: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_USER_NAME, newUserName)
        state = state.copy(userName = newUserName)
    }

    fun saveNewWorkOut(newWorkOut: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newWorkOut)
        state = state.copy(workOut = newWorkOut)
        updateDailyGoals()
    }

    fun saveNewGoals(newGoals: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newGoals)
        state = state.copy(dailyGoals = newGoals)
    }

    fun saveNewWeight(newWeight: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_WEIGHT, newWeight)
        state = state.copy(weight = newWeight)
        updateDailyGoals()
    }

    fun saveNewHeight(newHeight: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_HEIGHT, newHeight)
        state = state.copy(height = newHeight)
    }

    fun saveNewWakeUpTime(newWakeUpTime: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_WAKE_UP_TIME, newWakeUpTime)
        state = state.copy(wakeUpTime = newWakeUpTime)
    }

    fun saveNewSleepTime(newSleepTime: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_SLEEP_TIME, newSleepTime)
        state = state.copy(sleepTime = newSleepTime)
    }

    private fun updateDailyGoals() {
        val newDailyGoals = calculateDailyGoals(state.weight, state.workOut)
        state = state.copy(dailyGoals = newDailyGoals)
    }
}
