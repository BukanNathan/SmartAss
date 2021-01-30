package com.example.pethub_pembayaran_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_basket.*

class Basket : AppCompatActivity() {
    var count: Int = 0
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

    fun inc(view: View) {
        count++
        textView.text = count.toString()
        if (count<=0) {
            button2.isEnabled = false
        }
        else if (count>0) {
            button2.isEnabled = true
        }
    }
    fun dec(view: View) {
        count--
        if (count<=0) {
            button2.isEnabled = false
            textView.text = count.toString()
        }
        else if (count>0) {
            button2.isEnabled = true
            textView.text = count.toString()
        }
    }
}