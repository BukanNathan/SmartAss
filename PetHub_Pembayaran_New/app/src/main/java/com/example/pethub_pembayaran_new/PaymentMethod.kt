package com.example.pethub_pembayaran_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class PaymentMethod : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_method)
    }

    fun back (view: View) {
        var intentBack = Intent(this, Basket::class.java)
        startActivity(intentBack)
    }
}