package com.example.water_reminder.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.water_reminder.utils.SharedPrefHelper

/**
 * BroadcastReceiver responsible for receiving alarm notifications and scheduling the next alarm.
 */
class NotificationReceiver : BroadcastReceiver() {

    /**
     * Called when a broadcast is received. This method is invoked when the alarm is triggered.
     *
     * @param context The context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotificationReceiver", "Alarm received")

        // Start the service to show the notification
        val serviceIntent = Intent(context, MyService::class.java)
        serviceIntent.putExtra("should_send_notification", true) // Pass extra parameter
        context.startService(serviceIntent)

        // Reschedule the alarm
        scheduleNextAlarm(context)
    }

    /**
     * Schedules the next alarm based on the reminder interval.
     *
     * @param context The context in which the alarm is scheduled.
     */
    fun scheduleNextAlarm(context: Context) {
        // Get the reminder interval from SharedPreferences
        val intervalMinutes = SharedPrefHelper.readInt(SharedPrefHelper.PREF_REMINDER_INTERVAL, 30)

        // If interval is 0, skip scheduling
        if (intervalMinutes == 0) {
            Log.d("NotificationReceiver", "Reminder interval is zero, skipping notification scheduling")
            return
        }

        val intervalMillis = intervalMinutes * 60 * 1000L // Convert minutes to milliseconds
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Cancel any existing alarm to prevent duplicate alarms
        alarmManager.cancel(pendingIntent)

        try {
            // Schedule the next alarm
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, pendingIntent)
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, pendingIntent)
                }
            } else {
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + intervalMillis, intervalMillis, pendingIntent)
            }
            Log.d("NotificationReceiver", "Next alarm scheduled in $intervalMillis milliseconds")
        } catch (e: SecurityException) {
            // Handle SecurityException, maybe show a message to the user
            Log.e("NotificationReceiver", "SecurityException: ${e.message}")
        }
    }
}
