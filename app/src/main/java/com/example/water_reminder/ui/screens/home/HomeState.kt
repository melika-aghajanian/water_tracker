package com.example.water_reminder.ui.screens.home

import com.example.water_reminder.data.models.DailyHistory
import com.example.water_reminder.data.models.DrinkType

/**
 * Data class representing the state of the home screen.
 *
 * @property history The daily water intake history.
 * @property drinkTypes The available drink types.
 * @property percentage The percentage of daily water intake completion.
 * @property totalAmount The total amount of water intake for the day.
 */
data class HomeState(
    val history: DailyHistory? = null,
    val drinkTypes: List<DrinkType>? = null,
    val percentage: Float = 0F,
    val totalAmount: Int = 0
)
