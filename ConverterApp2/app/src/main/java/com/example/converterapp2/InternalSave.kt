package com.example.converterapp2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_internal_save.*
import kotlinx.android.synthetic.main.activity_internal_save.editText1
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

class InternalSave : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_save)

        SaveTxt.setOnClickListener {
            writeFileInternal()
        }
        SavePdf.setOnClickListener {
            writeFileInternal2()
        }
        readFile.setOnClickListener {
            readFileInternal()
        }
        allFileName.setOnClickListener {
            readDir()
        }
        deleteAll.setOnClickListener {
            delFiles()
        }
    }

    private fun delFiles() {
        if(fileList().size!=0){
            for(i in fileList()){
                deleteFile(i)
            }
            Toast.makeText(this, "ALL FILES DELETED", Toast.LENGTH_SHORT).show()
            editText1.text.clear()
            editText2.text.clear()
            editText3.text.clear()
        }
        else{
            Toast.makeText(this, "NO FILE TO DELETE", Toast.LENGTH_SHORT).show()
            editText1.text.clear()
            editText2.text.clear()
            editText3.text.clear()
        }
    }

    private fun readDir() {
        editText1.text.clear()
        if(fileList().size!=0){
            for(i in fileList()){
                editText1.setText("${editText1.text}\n$i")
            }
        }
        else{
            Toast.makeText(this, "FILE EMPTY", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFileInternal() {
        editText1.text.clear()
        try {
            var input = openFileInput("${editText3.text}.doc").apply {
                bufferedReader().useLines {
                    for (text in it.toList()){
                        editText1.setText("${editText1.text}\n$text")
                    }
                }
            }
        }catch (e : FileNotFoundException){
            Toast.makeText(this,"File is txt",Toast.LENGTH_SHORT).show()
        }catch (e : IOException) {
            Toast.makeText(this, "File Can't be read", Toast.LENGTH_SHORT).show()
        }

        try {
            var input = openFileInput("${editText3.text}.txt").apply {
                bufferedReader().useLines {
                    for (text in it.toList()){
                        editText1.setText("${editText1.text}\n$text")
                    }
                }
            }
        }catch (e : FileNotFoundException){
            Toast.makeText(this,"File is doc",Toast.LENGTH_SHORT).show()
        }catch (e : IOException) {
            Toast.makeText(this, "File Can't be read", Toast.LENGTH_SHORT).show()
        }

        editText3.text.clear()
    }

    private fun writeFileInternal2() {
        var output2 = openFileOutput("${editText2.text}.doc", Context.MODE_PRIVATE).apply {
            write(editText1.text.toString().toByteArray())
            close()
        }
        var myFile = File(this.filesDir, "${editText2}.doc")
        Log.w("OK",myFile.absolutePath)
        editText1.text.clear()
        Toast.makeText(this,"File ${editText2.text}.doc created", Toast.LENGTH_SHORT).show()
        editText2.text.clear()
    }

    private fun writeFileInternal() {
        var output = openFileOutput("${editText2.text}.txt", Context.MODE_PRIVATE).apply {
            write(editText1.text.toString().toByteArray())
            close()
        }
        var myFile = File(this.filesDir, "${editText2}.txt")
        Log.w("OK",myFile.absolutePath)
        editText1.text.clear()
        Toast.makeText(this,"File ${editText2.text}.txt created", Toast.LENGTH_SHORT).show()
        editText2.text.clear()
    }
}