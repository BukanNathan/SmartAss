package com.example.converterapp2

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file_list.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseFile.setOnClickListener {
            val intentChooseFile = Intent(this, FileType::class.java)
            startActivity(intentChooseFile)
        }
        fileList.setOnClickListener {
            val intentFileList = Intent(this, FileList::class.java)
            startActivity(intentFileList)
        }
        resizePic.setOnClickListener {
            val intentResizePic = Intent(this, ResizePic::class.java)
            startActivity(intentResizePic)
        }
        convertion.setOnClickListener {
            val intentConvert = Intent(this, ConvertFile::class.java)
            startActivity(intentConvert)
        }
        link_convertion.setOnClickListener {
            val intentlinkConvert = Intent(this, ConvertLink::class.java)
            startActivity(intentlinkConvert)
        }
        internalSave.setOnClickListener {
            val intentInternalSave = Intent(this, InternalSave::class.java)
            startActivity(intentInternalSave)
        }

/** Untuk memastikan alarm manager berjalan */
        var alarmIntent = Intent(this, MyMessage::class.java).let {
            it.action = MyMessage.ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(this, 101, it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE, 1)

        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC, cal.timeInMillis, 60000L, alarmIntent)
    }
}