package com.example.recyclerviewsnaphelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SimpleAdapter(
    private val listItem: List<String>
) : RecyclerView.Adapter<SimpleAdapter.MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_snap, parent, false))

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       holder.bindItem(listItem[position])
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textItemAdapter = itemView.findViewById<TextView>(R.id.text_item_snap)

        fun bindItem(textItem: String){
            textItemAdapter.text = textItem
        }

    }
}