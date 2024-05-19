package com.example.water_reminder.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefHelper {

    private var sharedPreferences: SharedPreferences? = null

    private const val PREF_NAME = "hydration-preferences"
    const val PREF_USER_NAME = "userName"
    const val PREF_WORK_OUT = "workOut"
    const val PREF_DAILY_GOAL = "daily_goal"
    const val PREF_WEIGHT = "weight"
    const val PREF_HEIGHT = "height"
    const val PREF_WAKE_UP_TIME = "wake_up_time"
    const val PREF_SLEEP_TIME = "sleep_time"
    const val DEFAULT_DAILY_GOAL = 2700

    fun initialize(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveString(key: String, value: String) {
        sharedPreferences?.edit()?.putString(key, value)?.apply()
    }

    fun readString(key: String, defaultValue: String): String {
        return sharedPreferences?.getString(key, defaultValue) ?: defaultValue
    }

    fun saveInt(key: String, value: Int) {
        sharedPreferences?.edit()?.putInt(key, value)?.apply()
    }

    fun readInt(key: String, defaultValue: Int): Int {
        return sharedPreferences?.getInt(key, defaultValue) ?: defaultValue
    }
}
