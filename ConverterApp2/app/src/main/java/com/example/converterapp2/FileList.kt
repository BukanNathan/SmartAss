package com.example.converterapp2

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FileList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        var AirplaneReceiver = MyAirplaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver,filter)


    }
}