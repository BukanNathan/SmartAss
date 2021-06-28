package com.example.converterapp2

import android.content.Context
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

class LoginFirebaseController (activityContext : Context){
    private val database = Firebase.database
//     getReference untuk mengambil atau membuat nama tabel
    private val ref = database.getReference("USERS")
    private var context = activityContext;
//    fungsi mutable karena memerlukan pasanagn key dan value
    private  var hasil = mutableMapOf<String,String>()
//     fungsi untuk menyimpan user dalam database
    fun saveUser(userName : String,email : String,status : Boolean) {

//        ref push untuk firebase mengenerate key untuk firebase kita
        val userID = ref.push().key.toString()
        val userData = LoginUser(userName,email,status)

//        .child() untuk menuliskan datanya ke dalam database
        ref.child(userID).setValue(userData).apply {
//            menampilkan toast jika complete atau failure
            addOnCompleteListener {
                Toast.makeText(context,"Data Tersimpan", Toast.LENGTH_SHORT).show()
            }
            addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }
            addOnCanceledListener {  }
            addOnSuccessListener {  }
        }
    }
//    memerlukan key terhadap user yang ingin dihapus
//    pembacaan dalam firebase
    private fun readUserName() : MutableMap<String,String> {

//        proses pembacaan semua data yang ada di dalam firebase
        ref.addValueEventListener(object : ValueEventListener {
//            onCancelled akan di jalanakn jika terjadi pembatalan pembaca
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
//            data change jika dilakukan pembacaan data
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    hasil.clear()
                    for(data in p0.children)
                    {
//                        data yang terdapat dalam json akan diambil dan kembalikan kembali ke data class
                        val user = data.getValue(LoginUser::class.java)
//                        data yang dikembalikan hanya key dengan username
                        user?.let { hasil.put(data.key.toString(),it.userName) }
                    }
                }
            }
        })
        return hasil
    }

//    fungsi untuk mendelete user
    fun deleteData(srcName: String) {
//    value di filter dengan scrName
        val key = readUserName().filterValues { it== srcName }.keys
//    ambil key yang pertama ditemukan remove value
//    prompt toast
        ref.child(key.first()).removeValue()
        Toast.makeText(context,"Deleted!!", Toast.LENGTH_SHORT).show()
        update()
    }
//    fungsi untuk menandakan user ada atu tidak
    fun isExist(srcName : String) :Boolean {
        if(readUserName().containsValue(srcName))
            return true
        return false
    }

    private fun update() {
        Toast.makeText(context,"Update", Toast.LENGTH_SHORT).show()
    }

    fun updateData(userName: String, email : String, status: Boolean) {
        val key = readUserName().filterValues { it== userName }.keys
        val userID = key.first()
        val userData = LoginUser(userName,email,status)
//        membuat value untuk key untuk membentuk json objek dengan isi userData
        ref.child(userID).setValue(userData).apply {
            addOnCompleteListener {
//                dijalankan jika penyimpanan data berhasil
                Toast.makeText(context,"update!!", Toast.LENGTH_SHORT).show()
            }
            addOnFailureListener {
//                dijalankan jika penyimpanan data gagal
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
        update()
    }
}