package com.example.water_reminder.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.water_reminder.R

class MyService : Service() {

    private val channelId = "ReminderChannel"
    private val notificationId = 101
    private lateinit var notificationManager: NotificationManager

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "Service created")
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Check the extra parameter to decide whether to send the notification
        val shouldSendNotification = intent?.getBooleanExtra("should_send_notification", false) ?: false
        if (shouldSendNotification) {
            sendNotification()
        }
        stopSelf() // Stop the service after checking the condition
        return START_NOT_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Reminder Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        val notificationText = "It's time to drink water!"
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Water Reminder")
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.ic_small_logo)
            .build()
        notificationManager.notify(notificationId, notification)
    }
}
