package com.example.water_reminder

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.water_reminder.utils.SharedPrefHelper
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Custom Application class for the Water Reminder app.
 * This class initializes necessary components and configurations
 * for the app including Dagger Hilt, WorkManager, Shared Preferences, and Firebase.
 */
@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    /**
     * The factory used to create workers.
     * This is injected using Dagger Hilt.
     */
    @Inject
    lateinit var workerFactory: WorkerFactory

    /**
     * Provides the WorkManager configuration for the application.
     *
     * @return The WorkManager configuration with a custom worker factory.
     */
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    /**
     * Called when the application is starting, before any other application objects have been created.
     * This is where you should initialize components that need to be setup at the start of the app.
     */
    override fun onCreate() {
        super.onCreate()
        SharedPrefHelper.initialize(this)
        WorkManager.initialize(this, workManagerConfiguration)
        Firebase.initialize(this)
    }
}
