package com.example.converterapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sq_lite_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SqLiteMain : AppCompatActivity() {
    var mySQLitedb: SqLiteMyDBHelper? = null
    var myFirstRunSharedPref : FirstRunSharedPref? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sq_lite_main)
        mySQLitedb = SqLiteMyDBHelper(this)
        myFirstRunSharedPref = FirstRunSharedPref(this)
        mySQLitedb?.deleteAll()
        myFirstRunSharedPref?.firstRun = true
        if (myFirstRunSharedPref!!.firstRun){
            var secondIntent = Intent(this,PreLoad::class.java)
            startActivity(secondIntent)
        }

        updateAdapter()
        btn_add.setOnClickListener {
            var userTmp = SqLiteUser(1)
            userTmp.nama = edit_text_name.text.toString()
            userTmp.email = edit_text_email.text.toString()
            var result = mySQLitedb?.adduser(userTmp)
            if (result != -1L) {
                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
            updateAdapter()
            edit_text_name.text.clear()
            edit_text_email.text.clear()
        }
        btn_del.setOnClickListener {
            var nama = spinner1.selectedItem.toString()
            if(nama != null || nama != ""){
                doAsync {
                    mySQLitedb?.deleteUser(nama)
                    updateAdapter()
                }
            }
        }
    }

    fun updateAdapter(){
        doAsync {
            var nameList = mySQLitedb?.viewAllName()?.toTypedArray()
            uiThread {
                if(spinner1 != null && nameList?.size != 0){
                    var arrayAdapter = ArrayAdapter(applicationContext,
                        android.R.layout.simple_spinner_dropdown_item,
                        nameList!!
                    )
                    spinner1.adapter = arrayAdapter
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
    }
}