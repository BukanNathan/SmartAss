package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file_list.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseFile.setOnClickListener {
            val intentChooseFile = Intent(this, FileType::class.java)
            startActivity(intentChooseFile)
        }
        fileList.setOnClickListener {
            val intentFileList = Intent(this, FileList::class.java)
            startActivity(intentFileList)
        }
        resizePic.setOnClickListener {
            val intentResizePic = Intent(this, ResizePic::class.java)
            startActivity(intentResizePic)
        }
        convertion.setOnClickListener {
            val intentConvert = Intent(this, ConvertFile::class.java)
            startActivity(intentConvert)
        }
        link_convertion.setOnClickListener {
            val intentlinkConvert = Intent(this, ConvertLink::class.java)
            startActivity(intentlinkConvert)
        }
        internalSave.setOnClickListener {
            val intentInternalSave = Intent(this, InternalSave::class.java)
            startActivity(intentInternalSave)
        }
    }
}