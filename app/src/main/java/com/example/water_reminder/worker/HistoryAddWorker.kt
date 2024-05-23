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

@HiltWorker
class HistoryAddWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    lateinit var historyRepository: DailyHistoryRepository

    override suspend fun doWork(): Result {
        WorkerHelper.createHistoryDrinkWorker(
            context = applicationContext,
            scheduleType = WorkerHelper.ScheduleType.SCHEDULED
        )

        try {
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

            return Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
    }

    companion object {
        const val UNIQUE_WORKER_NAME = "HISTORY_ADD_WORKER"
    }
}