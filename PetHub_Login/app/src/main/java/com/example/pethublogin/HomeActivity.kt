package com.example.pethublogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import com.example.pethublogin.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.header_nav.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.logout -> startActivity(Intent(this, LoginActivity::class.java))
            }
            it.isChecked = true
            drawer.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun closeDrawer(view: View) {
        drawer.closeDrawers()
    }
    fun startPh(view: View) {
        var intentPh = Intent(this, PetHealth::class.java)
        startActivity(intentPh)
    }
    fun startMsg(view: View) {
        startActivity(Intent(this, ChatActivity::class.java))
    }
}