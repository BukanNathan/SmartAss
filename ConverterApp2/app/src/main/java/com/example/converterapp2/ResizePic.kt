package com.example.converterapp2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_resize.*

private const val EXTRA_STATUS = "EXTRA_STATUS"
private const val EXTRA_STATUS2 = "EXTRA_STATUS2"
private const val REQUEST_CODE = 30

class ResizePic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resize)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        button.setOnClickListener {
            textView6.text = editTextNumber.text
            textView7.text = editTextNumber2.text
        }

        takePic.setOnClickListener{
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePicIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePicIntent, REQUEST_CODE)
            }
            else {
                Toast.makeText(this, "Unable to Open Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImg = data?.extras?.get("data") as Bitmap
            view.setImageBitmap(takenImg)
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
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