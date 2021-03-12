package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_file_type.*

const val EXTRA_FILE = "EXTRA_FILE"

class FileType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_type)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        button1.setOnClickListener {
            val intentFileType = Intent(this, ShowFileType::class.java)
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1) {
                val radio: RadioButton = findViewById(id)
                var file = File (radio.text.toString(), editText1.text.toString())
                intentFileType.putExtra(EXTRA_FILE, file)
                startActivity(intentFileType)
            }
            else {
                Toast.makeText(applicationContext, "File type not selected!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}