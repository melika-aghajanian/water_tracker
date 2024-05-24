package com.example.water_reminder.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.water_reminder.R

/**
 * Service responsible for sending water reminder notifications.
 */
class MyService : Service() {

    private val channelId = "ReminderChannel"
    private val notificationId = 101
    private lateinit var notificationManager: NotificationManager

    /**
     * Called when the service is first created. Performs initialization tasks.
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("MyService", "Service created")

        // Initialize the notification manager
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel
        createNotificationChannel()
    }

    /**
     * Called when the service is started.
     * @param intent The Intent supplied to startService(Intent), as given. This may be null if the service is being restarted after its process has gone away.
     * @param flags Additional data about this start request.
     * @param startId A unique integer representing this specific request to start.
     * @return The return value indicates what semantics the system should use for the service's current started state. It may be one of the constants associated with the START_CONTINUATION_MASK bits.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Check the extra parameter to decide whether to send the notification
        val shouldSendNotification = intent?.getBooleanExtra("should_send_notification", false) ?: false
        if (shouldSendNotification) {
            sendNotification()
        }
        stopSelf() // Stop the service after checking the condition
        return START_NOT_STICKY
    }

    /**
     * Creates the notification channel, required for devices running Android Oreo (API level 26) and higher.
     */
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

    /**
     * Sends the water reminder notification.
     */
    private fun sendNotification() {
        val notificationText = "It's time to drink water!"
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Water Reminder")
            .setContentText(notificationText)
            .setSmallIcon(R.drawable.ic_small_logo)
            .build()
        notificationManager.notify(notificationId, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
