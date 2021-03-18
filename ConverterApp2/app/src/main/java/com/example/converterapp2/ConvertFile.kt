package com.example.converterapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.converterapp2.fragments.FragmentA
import com.example.converterapp2.fragments.FragmentB
import com.example.converterapp2.fragments.InterfaceData

class ConvertFile : AppCompatActivity(), InterfaceData {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_file)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentA = FragmentA()
        supportFragmentManager.beginTransaction().replace(R.id.container, fragmentA).commit()
    }

    override fun sendData(edit: String) {
        val bundle = Bundle()
        bundle.putString("Text", edit)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentB = FragmentB()
        fragmentB.arguments = bundle
        transaction.replace(R.id.container, fragmentB)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}