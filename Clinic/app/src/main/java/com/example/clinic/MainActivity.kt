package com.example.clinic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inten1 = findViewById<Button>(R.id.map)
        inten1.setOnClickListener{
            val map = Intent(this, MapsFragment::class.java)
            startActivity(map)
        }
    }

}