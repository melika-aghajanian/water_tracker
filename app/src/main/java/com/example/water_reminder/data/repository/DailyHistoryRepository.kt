package com.example.water_reminder.data.repository

import android.util.Log
import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.models.DailyHistory
import java.util.*
import javax.inject.Inject

/**
 * Repository for managing daily drink history data.
 *
 * @param dailyHistoryDao The Data Access Object for daily drink history.
 */
class DailyHistoryRepository @Inject constructor(
    private val dailyHistoryDao: DailyDrinkDAO
) {

    /**
     * Retrieves the daily drink history for a specific date.
     *
     * @param date The date for which to retrieve the history.
     * @return The daily drink history for the specified date, or null if an error occurs.
     */
    suspend fun getHistory(date: Date): DailyHistory? {
        return try {
            dailyHistoryDao.getHistoryByDate(date)
        } catch (e: Exception) {
            Log.e(DailyHistoryRepository::class.simpleName, "getHistory: ${e.localizedMessage}")
            null
        }
    }

    /**
     * Retrieves all the daily drink histories.
     *
     * @return A list of all daily drink histories.
     */
    suspend fun getHistories(): List<DailyHistory> {
        return dailyHistoryDao.getAllHistories()
    }

    /**
     * Creates or updates a daily drink history entry.
     *
     * @param dailyHistory The daily drink history to create or update.
     */
    suspend fun createHistory(dailyHistory: DailyHistory) {
        try {
            dailyHistoryDao.createOrUpdateDrinkHistory(dailyHistory)
        } catch (e: Exception) {
            Log.e(DailyHistoryRepository::class.simpleName, "getHistory: ${e.localizedMessage}")
        }
    }
}
