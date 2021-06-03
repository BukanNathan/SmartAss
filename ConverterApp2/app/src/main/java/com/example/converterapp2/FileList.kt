package com.example.converterapp2

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_file_list.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

private var myIntentMPService: Intent? = null
class FileList : AppCompatActivity(),ConnectionReceiver.ConnectionReceiverListener, View.OnClickListener {
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
        contacts.setOnClickListener {
            val intentDetails = Intent(this, ContactDetails::class.java)
            startActivity(intentDetails)
        }
        doc.setOnClickListener {
            val intentTexts = Intent(this, ExternalTextFile::class.java)
            startActivity(intentTexts)
        }

        play.setOnClickListener(this)
        pause.setOnClickListener(this)

        roomDatabase.setOnClickListener {
            val intentRoomDatabase = Intent(this,RoomDatabase::class.java)
            startActivity(intentRoomDatabase)
        }

        sqLiteDatabase.setOnClickListener {
            val intentSqLiteDatabase = Intent(this, SqLiteMain::class.java)
            startActivity(intentSqLiteDatabase)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.play -> {
                if (play.text.toString().toUpperCase().equals("PLAY") ||
                        play.text.toString().toUpperCase().equals("RESUME")) {
                    play.text = "STOP"
                    myIntentMPService?.setAction(ACTION_PLAY)
                    startService(myIntentMPService)
                }
                else {
                    play.text = "PLAY"
                    myIntentMPService?.setAction(ACTION_STOP)
                    startService(myIntentMPService)
                }
            }
            R.id.pause -> {
                if (play.text.toString().toUpperCase().equals("STOP")) {
                    play.text = "RESUME"
                    myIntentMPService?.setAction(ACTION_PAUSE)
                    startService(myIntentMPService)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(myIntentMPService==null) {
            myIntentMPService = Intent(this, MyMPService::class.java)
            myIntentMPService?.setAction(ACTION_CREATE)
            startService(myIntentMPService)
        }
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

    override fun onDestroy() {
        super.onDestroy()
        stopService(myIntentMPService)
    }
}