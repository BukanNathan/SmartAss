package com.example.converterapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_room_database.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random

class RoomDatabase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database)

        var db = Room.databaseBuilder(
                this, RoomMyDBRoomHelper::class.java, "myroomdbex.db"
        ).build()

        btn_add.setOnClickListener {
            var hasil = ""
            doAsync {
                var userTMP = RoomUser(Random.nextInt())
                userTMP.name = edit_text_name.text.toString()
                userTMP.email = edit_text_email.text.toString()
                db.RoomUserDAO().insertAll(userTMP)
                for(allData in db.RoomUserDAO().getAllData()) {
                    hasil += "${allData}"
                }
                uiThread {
                    Log.w("Hasil DB", hasil)
                }
            }
        }

        btn_del.setOnClickListener {
            doAsync {
                db.RoomUserDAO().deleteAll(edit_text_name.text.toString())
            }
        }

        btn_update.setOnClickListener {
            doAsync {
                db.RoomUserDAO().update(edit_text_name.text.toString(), edit_text_email.text.toString())
            }
        }
    }
}