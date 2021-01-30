package com.example.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val back = findViewById<ImageButton>(R.id.imageBack)
        back.setOnClickListener {
            val intentback = Intent(this, Activity::class.java)
            startActivity(intentback)
        }

        /*val buttonhome = findViewById<ImageView>(R.id.homebtn)
        buttonhome.setOnClickListener {
            val intenthome = Intent(this, home::class.java)
            startActivity(intenthome)
        }*/
    }
}