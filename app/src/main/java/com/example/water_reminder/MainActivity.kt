package com.example.water_reminder

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkManager
import com.example.water_reminder.navigation.AppNavGraph
import com.example.water_reminder.notification.MyService
import com.example.water_reminder.notification.NotificationReceiver
import com.example.water_reminder.theme.WaterReminderTheme
import com.example.water_reminder.ui.components.AppBottomNav
import com.example.water_reminder.utils.SharedPrefHelper
import com.example.water_reminder.utils.worker.WorkerHelper
import com.example.water_reminder.worker.HistoryAddWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Main activity for the Water Reminder app.
 * This activity is the entry point of the application and handles navigation,
 * theme setting, splash screen, and scheduling of background tasks.
 */
@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is starting. This is where most initialization should go.
     * It sets up the content view, installs the splash screen, and starts necessary services and workers.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Installing Splash Screen
        installSplashScreen()

        setContent {
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                context.startService(Intent(context, MyService::class.java))
            }
            WaterReminderTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            backgroundColor = MaterialTheme.colors.primaryVariant,
                            elevation = 0.dp
                        ) {
                            AppBottomNav(
                                navController = navController
                            )
                        }
                    },
                    content = {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            AppNavGraph(navController = navController)
                        }
                    }
                )
            }
        }
        scheduleInitialAlarm()
        lifecycleScope.launch {
            initDatabaseWorker()
        }
    }

    /**
     * Called after onRestoreInstanceState, onRestart, or onPause, for your activity to start interacting with the user.
     * It checks the reminder interval and starts or stops the reminder service accordingly.
     */
    override fun onResume() {
        super.onResume()
        val interval = SharedPrefHelper.readInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, 30)
        if (interval > 0) {
            startReminderService()
        } else {
            stopReminderService()
        }
    }

    /**
     * Starts the reminder service to notify the user to drink water.
     */
    private fun startReminderService() {
        val serviceIntent = Intent(this, MyService::class.java)
        startService(serviceIntent)
    }

    /**
     * Stops the reminder service.
     */
    private fun stopReminderService() {
        val serviceIntent = Intent(this, MyService::class.java)
        stopService(serviceIntent)
    }

    /**
     * Initializes the database worker if it has not been initialized already.
     * This worker is responsible for adding drink history records.
     */
    private fun initDatabaseWorker() {
        if (WorkManager.getInstance(this)
                .getWorkInfosForUniqueWork(HistoryAddWorker.UNIQUE_WORKER_NAME).get().isEmpty()
        ) {
            WorkerHelper.createHistoryDrinkWorker(
                context = applicationContext,
                scheduleType = WorkerHelper.ScheduleType.NOW
            )
        }
    }

    /**
     * Schedules the initial alarm based on the reminder interval.
     * If the interval is greater than 0, it schedules the next alarm.
     */
    private fun scheduleInitialAlarm() {
        val interval = SharedPrefHelper.readInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, 30)
        if (interval > 0) {
            val receiver = NotificationReceiver()
            receiver.scheduleNextAlarm(this)
        }
    }
}
