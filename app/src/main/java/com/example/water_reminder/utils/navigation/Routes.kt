package com.example.water_reminder.utils.navigation

import androidx.annotation.DrawableRes
import com.example.water_reminder.R

/**
 * Sealed class representing the available navigation routes in the application.
 *
 * @property routeName The name of the navigation route.
 * @property icon The icon resource representing the navigation route.
 */
sealed class Routes(
    val routeName: String,
    @DrawableRes val icon: Int
) {
    /**
     * Object representing the home page navigation route.
     */
    object HomePage : Routes(routeName = "home_page", icon = R.drawable.ic_water_drop)

    /**
     * Object representing the history page navigation route.
     */
    object HistoryPage : Routes(routeName = "history_page", icon = R.drawable.ic_history)

    /**
     * Object representing the settings page navigation route.
     */
    object SettingsPage : Routes(routeName = "settings_page", icon = R.drawable.ic_settings_filled)
}
