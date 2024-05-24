package com.example.water_reminder.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Entity class representing daily drinking history.
 *
 * @property id The unique identifier for the daily history entry.
 * @property date The date of the daily history entry.
 * @property totalAmount The total amount of water consumed on that day.
 */
@Entity
data class DailyHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val date: Date,
    val totalAmount: Int = 0
)
