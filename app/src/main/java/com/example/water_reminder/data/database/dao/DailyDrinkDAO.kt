package com.example.water_reminder.data.database.dao

import androidx.room.*
import com.example.water_reminder.data.models.DailyHistory
import java.util.*

/**
 * Data Access Object (DAO) for DailyHistory entities.
 */
@Dao
interface DailyDrinkDAO {

    /**
     * Retrieves the daily history by date.
     *
     * @param date The date of the history to retrieve.
     * @return The DailyHistory object corresponding to the given date, if found.
     */
    @Query("SELECT * FROM DailyHistory WHERE date = :date")
    suspend fun getHistoryByDate(date: Date): DailyHistory

    /**
     * Retrieves all daily histories, ordered by date in descending order.
     *
     * @return A list of all DailyHistory objects.
     */
    @Query("SELECT * FROM DailyHistory ORDER BY date DESC")
    suspend fun getAllHistories(): List<DailyHistory>

    /**
     * Inserts or updates a daily history.
     *
     * @param drinkHistory The DailyHistory object to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdateDrinkHistory(drinkHistory: DailyHistory)
}
