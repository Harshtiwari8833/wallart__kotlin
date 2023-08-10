package com.maverickbits.wallart.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding

class WallAdapter(private val context: Context,private val list :ArrayList<WallModel>)
    :RecyclerView.Adapter<WallAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
        {
            val binding = WallLayoutBinding.bind(itemView)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.wall_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        Glide.with(context).load(currentItem.imgUrl).into(holder.binding.wallImg)

    }
}