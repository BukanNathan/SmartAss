package com.example.converterapp2

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHelper (context: Context, fileName : String) {
    val LINK = "LINK"

    private var myPreferences : SharedPreferences
    init {
        myPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    inline fun SharedPreferences.editMe(operator:(SharedPreferences.Editor)-> Unit) {
        val editMe = edit()
        operator(editMe)
        editMe.apply()
    }

    var link : String?
        get() = myPreferences.getString(LINK, "Empty")
        set(value) {
            myPreferences.editMe {
                it.putString(LINK, value)
            }
        }
    fun clearValue()
    {
        myPreferences.editMe {
            it.clear()
        }
    }
}