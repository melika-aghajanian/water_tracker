package com.example.water_reminder.data.database.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Type converter for Date objects to be used with Room database.
 */
class DateConverter {

    /**
     * Converts a Long value representing milliseconds since the epoch to a Date object.
     *
     * @param dateLong The Long value representing milliseconds since the epoch.
     * @return The corresponding Date object.
     */
    @TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    /**
     * Converts a Date object to a Long value representing milliseconds since the epoch.
     *
     * @param date The Date object to convert.
     * @return The Long value representing milliseconds since the epoch.
     */
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }
}
