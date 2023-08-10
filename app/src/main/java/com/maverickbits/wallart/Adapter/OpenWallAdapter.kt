package com.maverickbits.wallart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.EachWallpaperBinding

class OpenWallAdapter (val context: Context, private val list : List<WallModel>)
    : RecyclerView.Adapter<OpenWallAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenWallAdapter.ViewHolder {
        return ViewHolder( LayoutInflater.from(context).inflate(R.layout.each_wallpaper,parent,false))
    }

    override fun onBindViewHolder(holder: OpenWallAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(list[position].imgUrl).into(holder.binding.wallpaperImg)
    }



    override fun getItemCount(): Int {
       return list.size
    }
    class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
       val binding = EachWallpaperBinding.bind(itemView)
    }

}