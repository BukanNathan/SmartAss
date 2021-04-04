package com.example.converterapp2

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.BADGE_ICON_SMALL
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    var dataSpinner = arrayOf("Google", "Yahoo", "Facebook", "Instagram")
    val notification_channel1 = 1
    val notification_channel2 = 2
    var notificationManager: NotificationManager? = null;
    var myNotification: NotificationCompat.Builder? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //membuat spinner data
        val myAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                dataSpinner)
        myAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item)
        spinner?.adapter = myAdapter
        //menetukan radio yang di cek
        radioGroup.check(radioButton2.id)
        //notifikasi akan bekerja sebagai service, sehingga dapat dijalankan
        // walaupun aplikasi dalam keadaan tertutup
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        //fungsi user_defind / dibuat sendiri
        //berfungsi untuk membuat grup dan notification channel
        createNotificationGroup()
        createNotificationChannels()
        //mendaftarkan event clik untuk button dalam menampilkan notification
        btnNotification.setOnClickListener {
            if (pesan.text.length > 0) {
                var channel_id = ""
                var group_id = ""
                //mengecek versi oreo atau bukan, untuk membentuk notification
                // channel beredasarkan channel_id (setiap notifikasi memiliki id,
                //sehingga setiap notifikasi dapat dibedakan
                // satu dengan yang lain
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (radioButton1.isChecked)
                        group_id = radioButton1.text.toString()
                    else
                        group_id = radioButton2.text.toString()
                    channel_id = notificationManager!!.getNotificationChannel(
                            spinner.selectedItem.toString() + "_" + group_id
                    ).id
                }
                //membuat notifikasi
                myNotification = NotificationCompat
                        .Builder(this, channel_id)
                        .setContentTitle(spinner.selectedItem.toString())
                        .setContentText(pesan.text.toString())
                        .setGroup(group_id)
                        .setSmallIcon(R.drawable.wifi_logo)
                        .setBadgeIconType(BADGE_ICON_SMALL)
                        .setStyle(NotificationCompat.BigTextStyle()
                                .bigText("I can write so much text and longgeeeerrrrrrrrrrr text" +
                                                "it will show when you open notification." +
                                                "It will show on the notification"))

                //Menentukan Prioritasa
                               myNotification!!.priority = Notification.PRIORITY_HIGH
                                //Membentuk Aksi Intent
                               val url = "https://www.${spinner.selectedItem}.com"
                                val notifyIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                val notifyPandingIntent = PendingIntent.getActivities(
                                    this,notification_channel1,
                                    arrayOf(notifyIntent),
                                    PendingIntent.FLAG_UPDATE_CURRENT)
                                myNotification!!.setContentIntent(notifyPandingIntent)

                //                //Membuat Button Action
                                val yesIntent = Intent(this,NotificationReceiver::class.java)
                                yesIntent.setAction(EXTRA_YES)
                                val yesPendingIntent = PendingIntent.getBroadcast(this,0,yesIntent,0)

                                val noIntent = Intent(this,NotificationReceiver::class.java)
                                noIntent.setAction(EXTRA_NO)
                                val noPendingIntent = PendingIntent.getBroadcast(this,0,noIntent,0)
                //
                                myNotification!!.addAction(R.drawable.add_logo, "Yes", yesPendingIntent)
                                myNotification!!.addAction(R.drawable.more_logo_ver, "No", noPendingIntent)

                //penentuan id dan menampilkan notifikasi
                if (radioButton1.isChecked) {
                    //Membuat notifikasi tidak dapat dihilangkan
                    myNotification!!.setOngoing(true)
                    notificationManager!!.notify(notification_channel1, myNotification?.build())
                } else {
                    //Membuat notifikasi dapat dihilangkan
                    myNotification!!.setOngoing(false)
                    //menghilangkan Notifikasi ketika diklik
                    myNotification!!.setAutoCancel(true)
                    notificationManager!!.notify(notification_channel2, myNotification?.build())
                }
            }
            else{
                Toast.makeText(this,"Pesan Tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
        btnClearNotification.setOnClickListener {
            notificationManager!!.cancelAll()
        }
    }
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //membuat suara
            val notificationSound = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val att = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
            //membuat channel untuk 2 grup
            for (s in dataSpinner) {
                val Channel1 = NotificationChannel(
                        s +"_" + radioButton1.getText().toString(),
                        s,
                        NotificationManager.IMPORTANCE_HIGH)//level kepentingan notification
                Channel1.group = radioButton1.getText().toString()
                Channel1.vibrationPattern =
                        longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)//membuat getar
                Channel1.setSound(notificationSound,att); //menamapilkan suara
                Channel1.setLightColor(Color.RED); //menentukan warna lampu notifikasi
                Channel1.setShowBadge(false)
                Channel1.enableLights(true) // menyatakan lampu dapat menyala
                Channel1.enableVibration(true) // membuat handpone dapat bergetar
                //semua fungsi enable ini dapat anda atur langsung melalui fungsi pengaturan
                val Channel2 = NotificationChannel(
                        s + "_" + radioButton2.getText().toString(),
                        s,
                        NotificationManager.IMPORTANCE_DEFAULT)
                Channel2.enableLights(true)
                Channel2.enableVibration(true)
                Channel2.group = radioButton2.getText().toString()
                Channel2.vibrationPattern =
                        longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 100)
                Channel2.setShowBadge(false)
                //membuat notification 2 channel
                notificationManager!!.createNotificationChannel(Channel1)
                notificationManager!!.createNotificationChannel(Channel2)
            }
        }
    }

    private fun createNotificationGroup() {
        //memastikan android adalah android Oreo dan mendaftarkan 2 grup berbeda untuk notification
        // jika ada grup berbeda, anda dapat menambahkan list
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val list =  mutableListOf<NotificationChannelGroup>()
            list.add(NotificationChannelGroup(radioButton1.getText().toString(),
                    radioButton1.getText()))
            list.add(NotificationChannelGroup(radioButton2.getText().toString(),
                    radioButton2.getText()))
            notificationManager!!.createNotificationChannelGroups(list)
        }
    }
}