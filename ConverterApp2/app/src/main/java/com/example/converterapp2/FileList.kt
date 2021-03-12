package com.example.converterapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FileList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_list)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}