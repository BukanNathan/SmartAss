package com.example.converterapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_file_type_detail.*

class FileTypeDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_type_detail)

        val myListDetail : List<MyDetail> = listOf(
                MyDetail(size = "162.32 KB", date = " April 14, 2021 19:49", title = " FILE-20210414-WA00000", location = "Unknown", tag = "Unknown")
        )
        val detailAdapter = myAdapterRecyView(myListDetail)
        myRecyView.apply{
            layoutManager = LinearLayoutManager(this@FileTypeDetail)
            adapter = detailAdapter
        }
    }
}