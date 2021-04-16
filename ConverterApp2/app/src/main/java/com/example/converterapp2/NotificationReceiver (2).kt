package com.example.converterapp2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

const val EXTRA_YES = "YES"
const val EXTRA_NO  = "NO"
class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(p1?.action == EXTRA_YES)
            Log.w("Broadcast","Message : YES")
        else
            Log.w("Broadcast","Message : No")
    }
}