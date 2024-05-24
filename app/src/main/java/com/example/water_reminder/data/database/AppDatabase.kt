package com.example.water_reminder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.water_reminder.data.database.converter.DateConverter
import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.models.DailyHistory

/**
 * Room database class for the Water Reminder app.
 */
@Database(entities = [DailyHistory::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    /**
     * Returns the Data Access Object (DAO) for DailyDrink entities.
     */
    abstract fun dailyDrinkDao(): DailyDrinkDAO

    companion object {
        const val DATABASE_NAME = "WaterReminderDatabase"
    }
}
