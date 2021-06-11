package com.example.converterapp2

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_widget.*

class WidgetActivity : AppCompatActivity() {
    private lateinit var mySharedPref: fileSharedPre
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        mySharedPref = fileSharedPre(this)
        buttonPlus.setOnClickListener {
            mySharedPref.number += 1
            number.setText(mySharedPref.number.toString())
        }
        buttonMinus.setOnClickListener {
            mySharedPref.number -= 1
            number.setText(mySharedPref.number.toString())
        }
    }

    override fun onStop() {
        super.onStop()
        var appWidgetManager = AppWidgetManager.getInstance(this)
        var ids = appWidgetManager.getAppWidgetIds(ComponentName(this, MyNumberWidget::class.java))
        var updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(updateIntent)
    }

    override fun onResume() {
        number.setText(mySharedPref.number.toString())
        super.onResume()
    }
}