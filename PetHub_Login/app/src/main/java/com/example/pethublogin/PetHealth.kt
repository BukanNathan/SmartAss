package com.example.pethublogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_pet_health.*

class PetHealth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_health)

        search.queryHint = "Search for Vets..."
    }

    fun homePh(view: View) {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}