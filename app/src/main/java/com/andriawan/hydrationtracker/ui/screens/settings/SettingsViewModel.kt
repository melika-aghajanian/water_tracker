package com.andriawan.hydrationtracker.ui.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.andriawan.hydrationtracker.utils.SharedPrefHelper
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
        val dailyGoals = SharedPrefHelper.readInt(SharedPrefHelper.PREF_DAILY_GOAL, SharedPrefHelper.DEFAULT_DAILY_GOAL)
        val weight = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WEIGHT, 0)
        val height = SharedPrefHelper.readInt(SharedPrefHelper.PREF_HEIGHT, 0)
        val wakeUpTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_WAKE_UP_TIME, "")
        val sleepTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_SLEEP_TIME, "")
        ///////////////////////////
        state = state.copy(
            dailyGoals = dailyGoals,
            weight = weight,
            height = height,
            wakeUpTime = wakeUpTime,
            sleepTime = sleepTime
        )
    }

    fun saveNewGoals(newGoals: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newGoals)
        state = state.copy(dailyGoals = newGoals)
    }

    fun saveNewWeight(newWeight: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_WEIGHT, newWeight)
        state = state.copy(weight = newWeight)
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
}