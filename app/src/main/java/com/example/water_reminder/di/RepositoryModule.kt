package com.example.water_reminder.di

import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.repository.DailyHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing repository dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides the singleton instance of the DailyHistoryRepository.
     *
     * @param dailyDrinkDAO The DAO for daily drink history.
     * @return The singleton instance of the DailyHistoryRepository.
     */
    @Provides
    @Singleton
    fun providesDailyHistoryRepository(
        dailyDrinkDAO: DailyDrinkDAO
    ) : DailyHistoryRepository {
        return DailyHistoryRepository(dailyDrinkDAO)
    }
}
