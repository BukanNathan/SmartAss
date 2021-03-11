package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

private const val EXTRA_STATUS = "EXTRA_STATUS"
private const val EXTRA_STATUS2 = "EXTRA_STATUS2"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView6.text = editTextNumber.text
            textView7.text = editTextNumber2.text

            val intent1 = Intent(this, FileType::class.java)
            startActivity(intent1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS, textView6.text.toString())
        outState.putString(EXTRA_STATUS2, textView7.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView6.text = savedInstanceState.getString(EXTRA_STATUS)
        textView7.text = savedInstanceState.getString(EXTRA_STATUS2)
    }
}