package com.example.water_reminder.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Utility object for handling shared preferences.
 * This object provides functions to initialize shared preferences, save and read string and integer values.
 */
object SharedPrefHelper {

    private var sharedPreferences: SharedPreferences? = null

    private const val PREF_NAME = "hydration-preferences"

    /**
     * Key for storing user name in shared preferences.
     */
    const val PREF_USER_NAME = "userName"

    /**
     * Key for storing workout status in shared preferences.
     */
    const val PREF_WORK_OUT = "workOut"

    /**
     * Key for storing daily water intake goal in shared preferences.
     */
    const val PREF_DAILY_GOAL = "daily_goal"

    /**
     * Key for storing user weight in shared preferences.
     */
    const val PREF_WEIGHT = "weight"

    /**
     * Key for storing user height in shared preferences.
     */
    const val PREF_HEIGHT = "height"

    /**
     * Key for storing wake-up time in shared preferences.
     */
    const val PREF_WAKE_UP_TIME = "wake_up_time"

    /**
     * Key for storing sleep time in shared preferences.
     */
    const val PREF_SLEEP_TIME = "sleep_time"

    /**
     * Key for storing reminder interval in shared preferences.
     */
    const val PREF_REMINDER_INTERVAL = "reminderInterval"

    /**
     * Initializes shared preferences with the provided context.
     *
     * @param context The context used to initialize shared preferences.
     */
    fun initialize(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    /**
     * Saves a string value with the specified key in shared preferences.
     *
     * @param key The key used to store the value.
     * @param value The string value to be saved.
     */
    fun saveString(key: String, value: String) {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    /**
     * Reads a string value associated with the specified key from shared preferences.
     *
     * @param key The key used to retrieve the value.
     * @param defaultValue The default value to be returned if the key is not found.
     * @return The string value associated with the key, or the default value if the key is not found.
     */
    fun readString(key: String, defaultValue: String): String {
        return sharedPreferences?.getString(key, defaultValue) ?: defaultValue
    }

    /**
     * Saves an integer value with the specified key in shared preferences.
     *
     * @param key The key used to store the value.
     * @param value The integer value to be saved.
     */
    fun saveInt(key: String, value: Int) {
        sharedPreferences?.edit()?.putInt(key, value)?.apply()
    }

    /**
     * Reads an integer value associated with the specified key from shared preferences.
     *
     * @param key The key used to retrieve the value.
     * @param defaultValue The default value to be returned if the key is not found.
     * @return The integer value associated with the key, or the default value if the key is not found.
     */
    fun readInt(key: String, defaultValue: Int): Int {
        return sharedPreferences?.getInt(key, defaultValue) ?: defaultValue
    }
}
