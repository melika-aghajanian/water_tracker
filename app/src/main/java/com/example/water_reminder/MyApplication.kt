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

@HiltAndroidApp
class MyApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        SharedPrefHelper.initialize(this)
        WorkManager.initialize(this, workManagerConfiguration)
        Firebase.initialize(this)
    }
}