package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.RadioButton
import android.widget.RadioGroup
import kotlinx.android.synthetic.main.activity_file_type.*

const val EXTRA_FILE = "EXTRA_FILE"

class FileType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_type)

        button1.setOnClickListener {
            val intentFileType = Intent(this, ShowFileType::class.java)
            var id: Int = radioGroup.checkedRadioButtonId
            val radio: RadioButton = findViewById(id)
            var file = File (radio.text.toString(), editText1.text.toString())
            intentFileType.putExtra(EXTRA_FILE, file)
            startActivity(intentFileType)
        }
    }

}