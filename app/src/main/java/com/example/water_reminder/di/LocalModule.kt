package com.example.water_reminder.di

import android.content.Context
import androidx.room.Room
import com.example.water_reminder.data.database.AppDatabase
import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing local database dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    /**
     * Provides the singleton instance of the local database.
     *
     * @param context The application context.
     * @return The singleton instance of the local database.
     */
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    /**
     * Provides the DAO (Data Access Object) for daily drink history.
     *
     * @param appDatabase The local database instance.
     * @return The DAO for daily drink history.
     */
    @Provides
    @Singleton
    fun providesDailyHistoryDao(appDatabase: AppDatabase): DailyDrinkDAO {
        return appDatabase.dailyDrinkDao()
    }
}
