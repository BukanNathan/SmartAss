package com.example.converterapp2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BADGE_ICON_SMALL
import androidx.core.app.TaskStackBuilder
import kotlinx.android.synthetic.main.activity_file_type.*

const val EXTRA_FILE = "EXTRA_FILE"

class FileType : AppCompatActivity() {

    private val channelID = "com.example.converterapp2.channel1"
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_type)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        button1.setOnClickListener {
            val intentFileType = Intent(this, ShowFileType::class.java)
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1) {
                val radio: RadioButton = findViewById(id)
                var file = File (radio.text.toString(), editText1.text.toString())
                intentFileType.putExtra(EXTRA_FILE, file)
                startActivity(intentFileType)
            }
            else {
                Toast.makeText(applicationContext, "File type not selected!", Toast.LENGTH_SHORT).show()
            }

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannel(channelID,"Demo Channel","This is a demo")
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        val tapResultIntent = Intent(this,FileTypeDetail::class.java)
        val myPendingIntent: PendingIntent? = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(tapResultIntent)
                .getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT)

        //action button 1
        val intent2 = Intent(this,FileTypeDetail::class.java)
        val pendingIntent2: PendingIntent? = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(intent2)
                .getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)

        val action2:NotificationCompat.Action=
                NotificationCompat.Action.Builder(0,"Details",pendingIntent2).build()

        //action button 2
        val intent3 = Intent(this,FileList::class.java)
        val pendingIntent3:PendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent3,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action3:NotificationCompat.Action=
                NotificationCompat.Action.Builder(0,"Transfer Files",pendingIntent3).build()

        val notification = NotificationCompat.Builder(this@FileType,channelID)
                .setContentTitle("Conversion Complete")
                .setContentText("File converted to ${editText1.text.toString()}")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setBadgeIconType(BADGE_ICON_SMALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(myPendingIntent)
                .addAction(action2)
                .addAction(action3)
                .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChannel(id: String , name : String , channelDescription : String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
                setShowBadge(true)
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}