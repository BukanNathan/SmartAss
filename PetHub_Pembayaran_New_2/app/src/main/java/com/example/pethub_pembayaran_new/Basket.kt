package com.example.pethub_pembayaran_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Basket : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
    }
    fun startSecond(view: View) {
        var intentSecond = Intent(this, PaymentMethod::class.java)
        startActivity(intentSecond)
    }
    fun startThird(view: View) {
        var intentThird = Intent(this, PaymentHistory::class.java)
        startActivity(intentThird)
    }
}