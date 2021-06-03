package com.example.converterapp2

import android.content.Context

class FirstRunSharedPref(context: Context) {
    private val keyPref = "FIRST RUN"
    private var mySharedPref = context.getSharedPreferences(
        "SharedPrefFile", Context.MODE_PRIVATE
    )
    var firstRun : Boolean
    get() = mySharedPref.getBoolean(keyPref,true)
    set(value) {mySharedPref.edit().putBoolean(keyPref,value).commit()}
}