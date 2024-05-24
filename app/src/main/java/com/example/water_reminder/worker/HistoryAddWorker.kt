package com.example.water_reminder.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.water_reminder.data.models.DailyHistory
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.utils.DateFormatter
import com.example.water_reminder.utils.worker.WorkerHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Worker class responsible for adding a daily history record.
 * This worker schedules itself for future execution and ensures that a history record
 * exists for the current date.
 *
 * @property historyRepository Repository for accessing daily history records.
 *
 * @constructor Creates an instance of [HistoryAddWorker].
 *
 * @param context The context of the application.
 * @param workerParams The parameters to setup the work.
 */
@HiltWorker
class HistoryAddWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    lateinit var historyRepository: DailyHistoryRepository

    /**
     * Performs the background work.
     * This function schedules the worker for future execution and ensures a history record exists for today.
     *
     * @return A [Result] indicating the success or failure of the work.
     */
    override suspend fun doWork(): Result {
        WorkerHelper.createHistoryDrinkWorker(
            context = applicationContext,
            scheduleType = WorkerHelper.ScheduleType.SCHEDULED
        )

        return try {
            withContext(Dispatchers.IO) {
                val date = DateFormatter.formatDate(Date()) ?: Date()
                val history = historyRepository.getHistory(date)

                if (history == null) {
                    val dailyHistory = DailyHistory(
                        id = null,
                        date = date,
                        totalAmount = 0
                    )
                    historyRepository.createHistory(dailyHistory)
                }
            }
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }

    companion object {
        /**
         * Unique name for this worker to ensure it is not duplicated.
         */
        const val UNIQUE_WORKER_NAME = "HISTORY_ADD_WORKER"
    }
}
