package com.example.water_reminder.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility object for formatting dates.
 * This object provides functions to format dates into desired string representations.
 */
object DateFormatter {

    /**
     * Formats the given [date] object into a standardized date format.
     *
     * @param date The date to be formatted.
     * @return The formatted date object, or null if an error occurs during formatting.
     */
    fun formatDate(date: Date): Date? {
        return try {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val resultString = simpleDateFormat.format(date)

            simpleDateFormat.parse(resultString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Formats the given [date] object into a string representation.
     *
     * @param date The date to be formatted.
     * @return The formatted date string, or an error message if formatting fails.
     */
    fun formatDateToString(date: Date): String {
        return try {
            val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val resultString = simpleDateFormat.format(date)

            resultString
        } catch (e: Exception) {
            Log.e(DateFormatter::class.simpleName, "formatDateToString: ${e.localizedMessage}")
            "Invalid date format!"
        }
    }
}
