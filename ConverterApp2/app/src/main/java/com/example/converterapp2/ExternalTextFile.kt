package com.example.converterapp2

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_external_text_file.*
import java.io.File

@RequiresApi(Build.VERSION_CODES.M)
class ExternalTextFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_text_file)
        
        saveButton.setOnClickListener {
            if (isExternalStorageReadable()) {
                writeFileExternal()
            }
        }
        readButton.setOnClickListener {
            if (isExternalStorageReadable()) {
                readFileExternal()
            }
        }
    }
    
    private fun readFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        editText.text.clear()
        var readFile = ""
        File(myDir,"${fileRead.text}.txt").forEachLine {
            readFile+="$it\n"
        }
        editText.setText(readFile)
    }
    private fun writeFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        if (!myDir.exists()) {
            myDir.mkdir()
        }
        File(myDir, "${fileSave.text}.txt").apply {
            writeText(editText.text.toString())
        }
        editText.text.clear()
    }
    private fun isExternalStorageReadable():Boolean {
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
        }
        var state = Environment.getExternalStorageState()
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            123 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}