package com.andriawan.water_reminder.ui.screens.home

import com.andriawan.water_reminder.data.models.DailyHistory
import com.andriawan.water_reminder.data.models.DrinkType

data class HomeState(
    val history: DailyHistory? = null,
    val drinkTypes: List<DrinkType>? = null,
    val percentage: Float = 0F,
    val totalAmount: Int = 0
)