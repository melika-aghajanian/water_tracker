package com.example.water_reminder.ui.screens.history

import com.example.water_reminder.data.models.DailyHistory

/**
 * Data class representing the state of the history screen.
 *
 * @property histories A list of daily history records, can be null if no data is available.
 */
data class HistoryState(
    val histories: List<DailyHistory>? = null
)
