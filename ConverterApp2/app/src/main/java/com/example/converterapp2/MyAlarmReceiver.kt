package com.example.converterapp2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyAlarmReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val notifyId = 12345
        val channelId = "channel_01"
        val name = "ON"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(channelId, name, importance)
        var mBuilder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.more_logo_ver)
                .setContentText(intent?.getStringExtra(EXTRA_PESAN) ?: "Tidak ada pesan")
                .setContentTitle("Alarm Manager")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        var mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for (s in mNotificationManager.notificationChannels) {
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        mNotificationManager.createNotificationChannel(nNotifyChannel)
        mNotificationManager.notify(notifyId, mBuilder.build())
    }
}