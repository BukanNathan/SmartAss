package com.example.converterapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recy_view_file_type_detail.view.*

class myAdapterRecyView(private val detail: List<MyDetail>) : RecyclerView.Adapter<myHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myHolder {
        return myHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recy_view_file_type_detail,parent,false)
        )
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.bindDetail(detail[position])
    }

    override fun getItemCount(): Int = detail.size
}

class myHolder(view:View) : RecyclerView.ViewHolder(view){
    private val sizeName = view.textView8
    private val dateName = view.textView9
    private val titleName = view.textView10
    private val locationName = view.textView11
    private val tagName = view.textView12

    fun bindDetail(tmp: MyDetail){
        sizeName.text = "${sizeName.text} : ${tmp.size}"
        dateName.text = "${dateName.text} : ${tmp.date}"
        titleName.text = "${titleName.text} : ${tmp.title}"
        locationName.text = "${locationName.text} : ${tmp.location}"
        tagName.text = "${tagName.text} : ${tmp.tag}"
    }
}