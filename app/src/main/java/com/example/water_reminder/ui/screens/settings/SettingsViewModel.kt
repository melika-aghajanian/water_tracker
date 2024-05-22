package com.example.water_reminder.ui.screens.settings

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.water_reminder.notification.NotificationReceiver
import com.example.water_reminder.utils.SharedPrefHelper
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val application: Application

) : ViewModel() {
    var state by mutableStateOf(SettingsState())
        private set

    init {
        initData()
    }

    private fun initData() {
        val userName = SharedPrefHelper.readString(SharedPrefHelper.PREF_USER_NAME, "")
        val workOut = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WORK_OUT,0)
        val dailyGoals = SharedPrefHelper.readInt(SharedPrefHelper.PREF_DAILY_GOAL, SharedPrefHelper.DEFAULT_DAILY_GOAL)
        val weight = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WEIGHT, 0)
        val height = SharedPrefHelper.readInt(SharedPrefHelper.PREF_HEIGHT, 0)
        val wakeUpTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_WAKE_UP_TIME, "")
        val sleepTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_SLEEP_TIME, "")
        val reminderInterval = SharedPrefHelper.readInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, 0)

        state = state.copy(
            userName = userName,
            workOut = workOut,
            dailyGoals = dailyGoals,
            weight = weight,
            height = height,
            wakeUpTime = wakeUpTime,
            sleepTime = sleepTime,
            reminderInterval = reminderInterval
        )
    }

    fun saveNewUserName(newUserName: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_USER_NAME, newUserName)
        state = state.copy(userName = newUserName)
    }

    fun saveNewWorkOut(newWorkOut: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newWorkOut)
        state = state.copy(workOut = newWorkOut)
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

    fun saveNewReminderInterval(newInterval: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, newInterval)
        state = state.copy(reminderInterval = newInterval)
        // Reschedule the alarm
        val context = application.applicationContext
        val receiver = NotificationReceiver()
        receiver.scheduleNextAlarm(context)
    }

}
