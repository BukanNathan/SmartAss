package com.example.converterapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_recy_view_contacts.view.*

class myAdapterRecyView2 (private val contact: List<myContact>) : RecyclerView.Adapter<myHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder2 {
        return myHolder2(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recy_view_contacts,parent,false)
        )
    }

    override fun onBindViewHolder(holder: myHolder2, position: Int) {
        holder.bindContact(contact[position])
    }

    override fun getItemCount(): Int = contact.size
}

//menghubungkan data dengan view
class myHolder2(view: View) : RecyclerView.ViewHolder(view) {
    private val contactName = view.nama
    private val contactNumber = view.nomor

    fun bindContact(tmp: myContact) {
        contactName.text = "${contactName.text} : ${tmp.name}"
        contactNumber.text = "${contactNumber.text} : ${tmp.no}"
    }
}