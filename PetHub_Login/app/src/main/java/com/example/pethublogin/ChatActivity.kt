package com.example.pethublogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }

    fun backChat (view: View) {
        startActivity(Intent(this, PetHealth::class.java))
    }

    fun homeChat (view: View) {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}