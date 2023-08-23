package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.maverickbits.wallart.Activities.OpenWallActivity
import com.maverickbits.wallart.Api.WallModel
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WallAdapter(private val context: Context,private val list :ArrayList<Wallpaper>)
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


    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = list[position]

        Glide.with(context).load(currentItem.imgurl)
//            .apply(RequestOptions().override(200, 270))
            .centerCrop()
            .into(holder.binding.wallImg)

        holder.binding.wallcardView.setOnClickListener{
            val intent = Intent(context, OpenWallActivity ::class.java)

            intent.putExtra("wall_pos", position)
            context.startActivity(intent)
        }



    }
}