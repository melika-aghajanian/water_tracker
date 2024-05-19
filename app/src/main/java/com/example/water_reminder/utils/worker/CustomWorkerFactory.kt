package com.example.water_reminder.utils.worker

import android.content.Context
import androidx.work.*
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.worker.HistoryAddWorker
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val repository: DailyHistoryRepository
): WorkerFactory() {

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