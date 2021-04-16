package com.example.product

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.rd.draw.controller.DrawController

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
        val op2 = findViewById<ViewFlipper>(R.id.carousel)
        op2.setOnClickListener{
            val inten2 = Intent(this, Activity2::class.java)
            startActivity(inten2)
        }
        val sale1 = findViewById<Button>(R.id.sale1)
        sale1.setOnClickListener{
            val isale1 = Intent(this, product_3::class.java)
            startActivity(isale1)
        }
        val pop1 = findViewById<Button>(R.id.pop1)
        pop1.setOnClickListener{
            val ipop1 = Intent(this, product_3::class.java)
            startActivity(ipop1)
        }
        val sale2 = findViewById<Button>(R.id.sale2)
        sale2.setOnClickListener{
            val isale2 = Intent(this, product_2::class.java)
            startActivity(isale2)
        }
        val pop2 = findViewById<Button>(R.id.pop2)
        pop1.setOnClickListener{
            val ipop2 = Intent(this, product_2::class.java)
            startActivity(ipop2)
        }
        val sale3 = findViewById<Button>(R.id.sale3)
        sale3.setOnClickListener{
            val isale3 = Intent(this, Activity3::class.java)
            startActivity(isale3)
        }
        val pop3 = findViewById<Button>(R.id.pop3)
        pop3.setOnClickListener{
            val ipop3 = Intent(this, Activity3::class.java)
            startActivity(ipop3)
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