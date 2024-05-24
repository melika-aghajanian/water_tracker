package com.example.water_reminder.utils.worker

import android.content.Context
import androidx.work.*
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.worker.HistoryAddWorker
import javax.inject.Inject

/**
 * Factory class for creating custom workers with dependencies injected.
 * This factory is responsible for creating worker instances and injecting dependencies as needed.
 *
 * @property repository The repository for accessing daily history records.
 * @constructor Creates an instance of [CustomWorkerFactory] with the provided repository dependency.
 */
class CustomWorkerFactory @Inject constructor(
    private val repository: DailyHistoryRepository
) : WorkerFactory() {

    /**
     * Creates a custom worker instance with injected dependencies.
     *
     * @param appContext The application context.
     * @param workerClassName The class name of the worker to be created.
     * @param workerParameters The parameters for setting up the worker.
     * @return The created worker instance with injected dependencies.
     */
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val workerClass = Class.forName(workerClassName).asSubclass(CoroutineWorker::class.java)
        val constructor = workerClass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is HistoryAddWorker -> {
                instance.historyRepository = repository
            }
        }

        return instance
    }
}
