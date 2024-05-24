package com.example.water_reminder.ui.screens.settings

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.water_reminder.notification.NotificationReceiver
import com.example.water_reminder.utils.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for managing settings data and interactions in the Settings screen.
 *
 * @property application The application context.
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {

    /**
     * The current state of settings.
     */
    var state by mutableStateOf(SettingsState())
        private set

    init {
        initData()
    }

    /**
     * Initializes the settings data.
     */
    private fun initData() {
        val userName = SharedPrefHelper.readString(SharedPrefHelper.PREF_USER_NAME, "")
        val workOut = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WORK_OUT, 0)
        val weight = SharedPrefHelper.readInt(SharedPrefHelper.PREF_WEIGHT, 0)
        val height = SharedPrefHelper.readInt(SharedPrefHelper.PREF_HEIGHT, 0)
        val wakeUpTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_WAKE_UP_TIME, "")
        val sleepTime = SharedPrefHelper.readString(SharedPrefHelper.PREF_SLEEP_TIME, "")
        val reminderInterval = SharedPrefHelper.readInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, 0)

        val dailyGoals = calculateDailyGoals(weight, workOut)
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, dailyGoals) // Save calculated goals in SharedPref

        state = state.copy(
            userName = userName,
            workOut = workOut,
            weight = weight,
            height = height,
            wakeUpTime = wakeUpTime,
            sleepTime = sleepTime,
            reminderInterval = reminderInterval,
            dailyGoals = dailyGoals
        )
    }

    /**
     * Calculates the daily water intake goals based on weight and workout duration.
     *
     * @param weight The user's weight.
     * @param workOut The user's workout duration.
     * @return The calculated daily water intake goal.
     */
    private fun calculateDailyGoals(weight: Int, workOut: Int): Int {
        return (weight * 100 / 3) + (workOut / 6 * 7)
    }

    /**
     * Saves the new user name.
     *
     * @param newUserName The new user name to be saved.
     */
    fun saveNewUserName(newUserName: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_USER_NAME, newUserName)
        state = state.copy(userName = newUserName)
    }

    /**
     * Saves the new workout duration and updates the daily water intake goals.
     *
     * @param newWorkOut The new workout duration to be saved.
     */
    fun saveNewWorkOut(newWorkOut: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_WORK_OUT, newWorkOut)
        state = state.copy(workOut = newWorkOut)
        updateDailyGoals()
    }

    /**
     * Saves the new daily water intake goals.
     *
     * @param newGoals The new daily water intake goals to be saved.
     */
    fun saveNewGoals(newGoals: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newGoals)
        state = state.copy(dailyGoals = newGoals)
    }

    /**
     * Saves the new weight and updates the daily water intake goals.
     *
     * @param newWeight The new weight to be saved.
     */
    fun saveNewWeight(newWeight: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_WEIGHT, newWeight)
        state = state.copy(weight = newWeight)
        updateDailyGoals()
    }

    /**
     * Saves the new height.
     *
     * @param newHeight The new height to be saved.
     */
    fun saveNewHeight(newHeight: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_HEIGHT, newHeight)
        state = state.copy(height = newHeight)
    }

    /**
     * Saves the new wake-up time.
     *
     * @param newWakeUpTime The new wake-up time to be saved.
     */
    fun saveNewWakeUpTime(newWakeUpTime: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_WAKE_UP_TIME, newWakeUpTime)
        state = state.copy(wakeUpTime = newWakeUpTime)
    }

    /**
     * Saves the new sleep time.
     *
     * @param newSleepTime The new sleep time to be saved.
     */
    fun saveNewSleepTime(newSleepTime: String) {
        SharedPrefHelper.saveString(SharedPrefHelper.PREF_SLEEP_TIME, newSleepTime)
        state = state.copy(sleepTime = newSleepTime)
    }

    /**
     * Saves the new reminder interval and reschedules the notification alarm.
     *
     * @param newInterval The new reminder interval to be saved.
     */
    fun saveNewReminderInterval(newInterval: Int) {
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, newInterval)
        state = state.copy(reminderInterval = newInterval)
        // Reschedule the alarm
        val context = application.applicationContext
        val receiver = NotificationReceiver()
        receiver.scheduleNextAlarm(context)
    }

    /**
     * Updates the daily water intake goals based on weight and workout duration changes.
     */
    private fun updateDailyGoals() {
        val newDailyGoals = calculateDailyGoals(state.weight, state.workOut)
        SharedPrefHelper.saveInt(SharedPrefHelper.PREF_DAILY_GOAL, newDailyGoals) // Save the updated goals in SharedPref
        state = state.copy(dailyGoals = newDailyGoals)
    }
}
