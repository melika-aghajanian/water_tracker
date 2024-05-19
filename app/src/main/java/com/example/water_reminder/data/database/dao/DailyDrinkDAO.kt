package com.example.water_reminder.data.database.dao

import androidx.room.*
import com.example.water_reminder.data.models.DailyHistory
import java.util.*

@Dao
interface DailyDrinkDAO {

    @Query("SELECT * FROM DailyHistory WHERE date = :date")
    suspend fun getHistoryByDate(date: Date): DailyHistory

    @Query("SELECT * FROM DailyHistory ORDER BY date DESC")
    suspend fun getAllHistories(): List<DailyHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdateDrinkHistory(drinkHistory: DailyHistory)
}