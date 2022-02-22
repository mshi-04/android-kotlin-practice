package com.example.implementationtest.utility

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.implementationtest.R
import com.example.implementationtest.activity.MainActivity

class PushNotification(private val context: Context) {
    private val CHANNEL_ID: String = "default"

    fun send() {
        createNotificationChannel()

        val intent: Intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val builder: NotificationCompat.Builder = createNotificationBuilder(pendingIntent)

        val notificationManager: NotificationManagerCompat =
            NotificationManagerCompat.from(context)
        notificationManager.notify(0, builder.build())
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: String = context.getString(R.string.default_channel)
            val descriptionText: String = context.getString(R.string.channel_description)
            val importance: Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotificationBuilder(pendingIntent: PendingIntent): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("テスト通知")
            .setContentText("これはテスト通知です")
            .setStyle(NotificationCompat.BigTextStyle().bigText("これはテスト通知です"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

}