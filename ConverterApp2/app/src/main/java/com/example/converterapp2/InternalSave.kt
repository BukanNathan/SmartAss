package com.example.converterapp2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_internal_save.*
import kotlinx.android.synthetic.main.activity_internal_save.editText1
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
            editText1.setText("ALL FILES DELETED")
        }
        else{
            editText1.setText("FILES EMPTY")
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
            editText1.setText("File Empty")
        }
    }

    private fun readFileInternal() {
        editText1.text.clear()
        try {
            var input = openFileInput("${editText3.text}.txt").apply {
                bufferedReader().useLines {
                    for (text in it.toList()){
                        editText1.setText("${editText1.text}\n$text")
                    }
                }
            }
        }catch (e : FileNotFoundException){
            Toast.makeText(this,"File Not Found",Toast.LENGTH_SHORT).show()
        }catch (e : IOException) {
            Toast.makeText(this, "File Can't be read", Toast.LENGTH_SHORT).show()
        }
    }

    private fun writeFileInternal2() {
        var output2 = openFileOutput("${editText2.text}.pdf", Context.MODE_PRIVATE).apply {
            write(editText1.text.toString().toByteArray())
            close()
        }
    }

    private fun writeFileInternal() {
        var output = openFileOutput("${editText2.text}.txt", Context.MODE_PRIVATE).apply {
            write(editText1.text.toString().toByteArray())
            close()
        }
        //var myFile = File(this.filesDir, "${editText2}.txt")
        //Log.w("Ok",myFile.absolutePath)
        //editText1.text.clear()
        //Toast.makeText(this,"File Saved", Toast.LENGTH_SHORT).show()
    }
}