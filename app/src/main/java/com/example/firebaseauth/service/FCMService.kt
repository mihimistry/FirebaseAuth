package com.example.firebaseauth.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.firebaseauth.R
import com.example.firebaseauth.activity.SendNotificationActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)

    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId= Random.nextInt()
        val intent = Intent(this, SendNotificationActivity::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this, Companion.CHANNEL_ID)
                .setContentTitle(message.data["title"])
                .setContentText(message.data["message"])
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager.notify(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(Companion.CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "my_channel"
    }
}
