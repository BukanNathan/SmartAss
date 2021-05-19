package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file_type.*
import kotlinx.android.synthetic.main.activity_show_file_type.*
import java.io.File

class ShowFileType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_file_type)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        var file: FileData? = intent.getParcelableExtra<FileData>(EXTRA_FILE)
        textView1.text = "File Type = ${file?.Type}, Extension = ${file?.Extension}"
    }
}