package com.example.pethub_pembayaran_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_payment_history.*

class PaymentHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        myPager.adapter = MyPagerAdapter(activity = this)

        TabLayoutMediator(tab_layout,myPager){tab, pos ->
            when(pos){
                0 -> tab.setText("Petcare")
                1 -> tab.setText("PetHealth")
                2 -> tab.setText("PetShop")
            }
        }.attach()
    }
}