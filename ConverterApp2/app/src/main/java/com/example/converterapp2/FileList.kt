package com.example.converterapp2

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_file_list.*

class FileList : AppCompatActivity(),ConnectionReceiver.ConnectionReceiverListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        var AirplaneReceiver = MyAirplaneReceiver()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirplaneReceiver,filter)

        baseContext.registerReceiver(ConnectionReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if(isConnected){
            connected.visibility = View.VISIBLE
            not_connected.visibility = View.INVISIBLE
        }
        else{
            connected.visibility = View.INVISIBLE
            not_connected.visibility = View.VISIBLE
        }
    }
}