package com.example.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class product_3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val back = findViewById<ImageButton>(R.id.imageBack)
        back.setOnClickListener {
            val intentback = Intent(this, Activity::class.java)
            startActivity(intentback)
        }

        val button = findViewById<ImageButton>(R.id.right_icon)
        button.setOnClickListener{
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
    }
}