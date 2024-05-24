package com.example.water_reminder.data.models

import androidx.annotation.DrawableRes

/**
 * Represents a drink type with its name, icon, and amount.
 *
 * @param name The name of the drink type.
 * @param icon The icon resource ID representing the drink type.
 * @param amount The amount of the drink type in milliliters.
 */
data class DrinkType(
    val name: String,
    @DrawableRes val icon: Int,
    val amount: Int
)
