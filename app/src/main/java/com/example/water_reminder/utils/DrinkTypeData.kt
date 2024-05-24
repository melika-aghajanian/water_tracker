package com.example.water_reminder.utils

import com.example.water_reminder.R
import com.example.water_reminder.data.models.DrinkType

/**
 * Utility object for providing data related to drink types.
 * This object provides a function to retrieve a list of predefined drink types.
 */
object DrinkTypeData {

    /**
     * Retrieves a list of predefined drink types.
     *
     * @return A list of [DrinkType] objects representing different drink types with their names, amounts, and icons.
     */
    fun getData(): List<DrinkType> {
        return listOf(
            DrinkType(
                name = "150ml",
                amount = 150,
                icon = R.drawable.ic_cups
            ),
            DrinkType(
                name = "250ml",
                amount = 250,
                icon = R.drawable.ic_bottle
            ),
            DrinkType(
                name = "330ml",
                amount = 330,
                icon = R.drawable.ic_glass
            )
        )
    }
}
