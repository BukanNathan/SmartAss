package com.example.converterapp2

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Display
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetails : AppCompatActivity(),
        //implementasi loaderManager
        LoaderManager.LoaderCallbacks<Cursor>
{
    //nama kolom dari contact
    var DisplayName = ContactsContract.Contacts.DISPLAY_NAME
    var Number = ContactsContract.CommonDataKinds.Phone.NUMBER
    //simpan
    var myListContact : MutableList<myContact> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        LoaderManager.getInstance(this).initLoader(1,null,this)
    }

    //loader di-create
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        //alamat contacts (data yang mau diambil)
        var MyContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //projection berupa array (kolom yang mau dibaca)
        var MyProjection = arrayOf(DisplayName, Number)
        return CursorLoader (this,MyContactUri,MyProjection,null,null,"$DisplayName ASC")
    }

    //loader selesai di-create
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        myListContact.clear()
        //pastikan data tidak null
        if (data != null) {
            data.moveToFirst()
            //selama data belum terakhir baca kolom data
            while (!data.isAfterLast) {
                myListContact.add(
                    myContact(
                        data.getString(data.getColumnIndex(DisplayName)),
                        data.getString(data.getColumnIndex(Number))
                    )
                )
                //menurunkan cursor ke baris berikut
                data.moveToNext()
            }
            //tampilkan data list ke dlm recycler view
            var contactAdapter = myAdapterRecyView2(myListContact)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@ContactDetails)
                adapter = contactAdapter
            }
        }
    }

    //data berubah
    override fun onLoaderReset(loader: Loader<Cursor>) {
        //panggil recycler view untuk update data
        recyclerView.adapter?.notifyDataSetChanged()
    }
}