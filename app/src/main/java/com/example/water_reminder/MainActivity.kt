package com.example.water_reminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkManager
import com.example.water_reminder.navigation.AppNavGraph
import com.example.water_reminder.theme.WaterReminderTheme
import com.example.water_reminder.ui.components.AppBottomNav
import com.example.water_reminder.utils.worker.WorkerHelper
import com.example.water_reminder.worker.HistoryAddWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Installing Splash Screen
        installSplashScreen()

        setContent {
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

        lifecycleScope.launch {
            initDatabaseWorker()
        }
    }

    /**
     * Create a function for scheduling create database every night
     * at 00:00:00 am
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
}