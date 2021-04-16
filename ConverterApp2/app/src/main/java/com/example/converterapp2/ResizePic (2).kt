package com.example.converterapp2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_resize.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


private const val EXTRA_STATUS = "EXTRA_STATUS"
private const val EXTRA_STATUS2 = "EXTRA_STATUS2"
private const val REQUEST_CODE = 30

class ResizePic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resize)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val addImg = findViewById<Button>(R.id.filter1);
        val addImg2 = findViewById<Button>(R.id.filter2);
        val addImg3 = findViewById<Button>(R.id.filter3);
        val addImg4 = findViewById<Button>(R.id.filter4);
        val viewImg = findViewById<ImageView>(R.id.viewFilter);
        var strURL = "https://i.ibb.co/VLrzf9V/pngwing-com-1.png"
        var strURL2 = "https://i.ibb.co/X5ZkYdk/pngwing-com.png"
        var strURL3 = "https://i.ibb.co/G3x0H82/instragram-love-filters.png"
        var strURL4 = "https://i.ibb.co/Zf7tpVJ/forest-border.png"

        addImg.setOnClickListener {
            Thread(Runnable{
                val bitmap = processBitMap(strURL)
                viewImg.post{
                    println("Menambahkan gambar");
                    viewImg.setImageBitmap(bitmap);
                }
            }).start()
        }

        addImg2.setOnClickListener {
            Thread(Runnable{
                val bitmap = processBitMap(strURL2)
                viewImg.post{
                    println("Menambahkan gambar");
                    viewImg.setImageBitmap(bitmap);
                }
            }).start()
        }

        addImg3.setOnClickListener {
            Thread(Runnable{
                val bitmap = processBitMap(strURL3)
                viewImg.post{
                    println("Menambahkan gambar");
                    viewImg.setImageBitmap(bitmap);
                }
            }).start()
        }

        addImg4.setOnClickListener {
            Thread(Runnable{
                val bitmap = processBitMap(strURL4)
                viewImg.post{
                    println("Menambahkan gambar");
                    viewImg.setImageBitmap(bitmap);
                }
            }).start()
        }

        button.setOnClickListener {
            textView6.text = editTextNumber.text
            textView7.text = editTextNumber2.text
        }

        takePic.setOnClickListener{
            val takePicIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePicIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePicIntent, REQUEST_CODE)
            }
            else {
                Toast.makeText(this, "Unable to Open Camera", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processBitMap(url : String) : Bitmap?{
        return try {
            val url = URL(url)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input:InputStream = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            myBitmap
        }catch (e:IOException){
            e.printStackTrace()
            null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImg = data?.extras?.get("data") as Bitmap
            view.setImageBitmap(takenImg)
        }
        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_STATUS, textView6.text.toString())
        outState.putString(EXTRA_STATUS2, textView7.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textView6.text = savedInstanceState.getString(EXTRA_STATUS)
        textView7.text = savedInstanceState.getString(EXTRA_STATUS2)
    }
}