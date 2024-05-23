package com.example.water_reminder.di

import androidx.work.WorkerFactory
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.utils.worker.CustomWorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCustomWorkerFactory(
        repository: DailyHistoryRepository
    ): WorkerFactory {
        return CustomWorkerFactory(repository)
    }
}