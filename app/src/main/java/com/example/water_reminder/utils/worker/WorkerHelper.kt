package com.example.water_reminder.utils.worker

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.water_reminder.worker.HistoryAddWorker
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Utility object for managing worker tasks.
 * This object provides functions to create and manage worker tasks, particularly for adding drink history.
 */
object WorkerHelper {

    /**
     * Creates a worker task for adding drink history.
     *
     * @param context The context used to access the WorkManager.
     * @param scheduleType The type of scheduling for the worker task.
     */
    fun createHistoryDrinkWorker(context: Context, scheduleType: ScheduleType) {
        val historyAddWorkerRequest = OneTimeWorkRequestBuilder<HistoryAddWorker>()

        if (scheduleType == ScheduleType.SCHEDULED) {
            // Set calendar at 00:00:00 am
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance()
            dueDate.set(Calendar.HOUR_OF_DAY, 0)
            dueDate.set(Calendar.MINUTE, 0)
            dueDate.set(Calendar.SECOND, 0)

            // Add one day for tomorrow schedule
            dueDate.add(Calendar.HOUR_OF_DAY, 24)

            // Convert time to millisecond and subtract with now time in millisecond
            val totalTimeMillis = dueDate.timeInMillis - currentDate.timeInMillis
            historyAddWorkerRequest.setInitialDelay(totalTimeMillis, TimeUnit.MILLISECONDS)
        }

        WorkManager.getInstance(context).enqueueUniqueWork(
            HistoryAddWorker.UNIQUE_WORKER_NAME,
            ExistingWorkPolicy.APPEND,
            historyAddWorkerRequest.build()
        )
    }

    /**
     * Enum class representing the type of scheduling for worker tasks.
     */
    enum class ScheduleType {
        /**
         * Immediate execution of the task.
         */
        NOW,

        /**
         * Scheduled execution of the task.
         */
        SCHEDULED
    }
}
