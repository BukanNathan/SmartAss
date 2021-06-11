package com.example.converterapp2

import android.content.Context
import android.content.SharedPreferences

class fileSharedPre(context: Context) {
    private val numberKey = "KeyRand"
    private val mySharedPref : SharedPreferences
    init {
        mySharedPref = context.getSharedPreferences("sharePrefNumber", Context.MODE_PRIVATE)
    }
    var number : Int
        get() = mySharedPref.getInt(numberKey, 0)
        set(value) {
            if (value<=0)
                mySharedPref.edit().putInt(numberKey, 0).apply()
            else
                mySharedPref.edit().putInt(numberKey, value).apply()
        }
}