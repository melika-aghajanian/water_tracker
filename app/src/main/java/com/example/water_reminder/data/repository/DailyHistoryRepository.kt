package com.example.water_reminder.data.repository

import android.util.Log
import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.models.DailyHistory
import java.util.*
import javax.inject.Inject

class DailyHistoryRepository @Inject constructor(
    private val dailyHistoryDao: DailyDrinkDAO
) {

    suspend fun getHistory(date: Date): DailyHistory? {
        return try {
            dailyHistoryDao.getHistoryByDate(date)
        } catch (e: Exception) {
            Log.e(DailyHistoryRepository::class.simpleName, "getHistory: ${e.localizedMessage}")
            null
        }
    }

    suspend fun getHistories(): List<DailyHistory> {
        return dailyHistoryDao.getAllHistories()
    }

    suspend fun createHistory(dailyHistory: DailyHistory) {
        try {
            dailyHistoryDao.createOrUpdateDrinkHistory(dailyHistory)
        } catch (e: Exception) {
            Log.e(DailyHistoryRepository::class.simpleName, "getHistory: ${e.localizedMessage}")
        }
    }
}