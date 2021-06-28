package com.example.converterapp2

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    lateinit var controller : LoginFirebaseController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toMain.setOnClickListener {
            val mainMenu = Intent(this, MainActivity::class.java)
            startActivity(mainMenu)
        }

        controller = LoginFirebaseController(this)

//         3 fungsi button untuk save update dan delete user
        submit.setOnClickListener {
            saveUser()
        }
        updateBtn.setOnClickListener {
            updateUser()
        }
        deleteBtn.setOnClickListener {
            deleteUser()
        }
    }
    private fun updateUser() {
        if(controller.isExist(inputID.text.toString()))
            AlertDialog.Builder(this).apply {
                setTitle("Data Found")
                setMessage("Apakah anda yakin untuk diubah?")
                        .setPositiveButton("YA", DialogInterface.OnClickListener { dialogInterface, i ->
                            val status = if(UpdateOn.isChecked) true else false
                            controller.updateData(inputID.text.toString(),
                                    updateEmail.text.toString(),
                                    status)
                        })
                        .setNegativeButton("TIDAK", DialogInterface.OnClickListener { dialogInterface, i ->
                            inputID.text.clear()
                        })
            }.show()
        else
            AlertDialog.Builder(this).apply {
                setTitle("Data Not Found")
                setMessage("Data Tidak Ditemukan!!")
                        .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                            inputID.text.clear()
                        })
            }.show()
    }

//    fungsi untuk medelete user sesuai yang dicari dalam textbox inputID
    private fun deleteUser() {
//    mencari tau user ada dalam firebase atau tidak
        if(controller.isExist(inputID.text.toString()))
//            jika ada jalankan alert dialog "Data Found"
            AlertDialog.Builder(this).apply {
                setTitle("Data Found")
                setMessage("Apakah anda yakin untuk dihapus?")
//                        jika user click ya maka panggil controller delete
                        .setPositiveButton("YA", DialogInterface.OnClickListener { dialogInterface, i ->
                            controller.deleteData(inputID.text.toString())
                        })
//                        jika tidak maka hanya menclear() input id
                        .setNegativeButton("TIDAK", DialogInterface.OnClickListener { dialogInterface, i ->
                            inputID.text.clear()
                        })
            }.show()
        else
//            jika user tidak ditemukan maka  jalankan alert dialog data not found
            AlertDialog.Builder(this).apply {
                setTitle("Data Not Found")
                setMessage("Data Tidak Ditemukan!!")
                        .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                            inputID.text.clear()
                        })
            }.show()
    }


    private fun saveUser() {
//        jika on tidak di click makan kembalikan nilai false, jika aktif maka true
        val status = if (on.isChecked) true else false
//        masukan nama user, email, dan status
        controller.saveUser(nama.text.toString(),
                userEmail.text.toString(),
                status)
//        masukan .clear() agar lebih rapi
        nama.text.clear()
        userEmail.text.clear()
        on.isChecked = true
    }
}