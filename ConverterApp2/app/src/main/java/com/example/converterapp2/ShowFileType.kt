package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file_type.*
import kotlinx.android.synthetic.main.activity_show_file_type.*

class ShowFileType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_file_type)

        var file: File? = intent.getParcelableExtra<File>(EXTRA_FILE)
        textView1.text = "File Type = ${file?.Type}, Extension = ${file?.Extension}"
    }
}