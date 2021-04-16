package com.example.clinic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val inten1 = findViewById<ImageView>(R.id.map)
//        inten1.setOnClickListener{
//            val map = Intent(this, MapsActivity::class.java)
//            startActivity(map)
//        }
    }

}
//MapsActivity
//private lateinit var mMap: GoogleMap
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = supportFragmentManager
//                .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near medan, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // Add a marker in medan and move the camera
//        val medan = LatLng(3.5952, 98.6722)
//        mMap.addMarker(MarkerOptions().position(medan).title("Marker in medan"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(medan))
//    }