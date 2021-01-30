package com.example.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity

class Activity : AppCompatActivity() {
    var carousel: ViewFlipper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_)
        val images = intArrayOf(R.drawable.bowl, R.drawable.food, R.drawable.necklace)
        carousel = findViewById(R.id.carousel)
        for (image in images) {
            flipperImages(image)
        }

        val op1 = findViewById<SearchView>(R.id.SearchBar)
         op1.setOnClickListener {
            val inten1 = Intent(this, Activity2::class.java)
            startActivity(inten1)
        }
        val op2 = findViewById<ViewFlipper>(R.id.carousel)
        op2.setOnClickListener{
            val inten2 = Intent(this, Activity3::class.java)
            startActivity(inten2)
        }
    }

    fun flipperImages(images: Int) {
        val imageView = ImageView(this)
        imageView.setBackgroundResource(images)
        carousel!!.addView(imageView)
        carousel!!.flipInterval = 2000
        carousel!!.isAutoStart = true

//    val ss = findViewById<SearchView>(R.id.SearchBar)
//        ss.setOnClickListener(new View.OnClickListener)
        //animation
//        carousel!!.setInAnimation(this, R.anim.slide_in_left)
//        carousel!!.setOutAnimation(this, R.anim.slide_out_right)
    }

    companion object {
        const val EXTRA_MESSAGE = "com.example.product."
    }
}