package com.kmv.android.doggies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kmv.android.doggies.R
import com.squareup.picasso.Picasso

class MyRecyclerAdapter(val shibesList: List<String>)
    : RecyclerView.Adapter<MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_shibe, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(shibesList[position])
    }

    override fun getItemCount(): Int {
        return shibesList.size
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var imageView: ImageView? = itemView.findViewById(R.id.imageView)

    fun bind(url: String) {
        Picasso.get().load(url).into(imageView)
    }
}