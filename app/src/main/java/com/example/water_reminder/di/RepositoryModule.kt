package com.example.water_reminder.di

import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.repository.DailyHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesDailyHistoryRepository(
        dailyDrinkDAO: DailyDrinkDAO
    ) : DailyHistoryRepository {
        return DailyHistoryRepository(dailyDrinkDAO)
    }
}