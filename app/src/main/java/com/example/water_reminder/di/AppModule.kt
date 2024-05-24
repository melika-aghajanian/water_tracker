package com.example.water_reminder.di

import androidx.work.WorkerFactory
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.utils.worker.CustomWorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a custom worker factory for creating workers with injected dependencies.
     *
     * @param repository The repository for accessing daily history data.
     * @return A WorkerFactory instance.
     */
    @Provides
    @Singleton
    fun providesCustomWorkerFactory(
        repository: DailyHistoryRepository
    ): WorkerFactory {
        return CustomWorkerFactory(repository)
    }
}
