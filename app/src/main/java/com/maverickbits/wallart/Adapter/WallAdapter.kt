package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.maverickbits.wallart.Activities.OpenWallActivity

import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent


class WallAdapter( val context: Context,private val updateFlagCallback: () -> Unit) :PagingDataAdapter<Wallpaper,WallAdapter.ViewHolder>(COMPARATOR)
{

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
        {
            val binding = WallLayoutBinding.bind(itemView)
        }
    companion object {
        private val COMPARATOR = object : DiffUtil. ItemCallback<Wallpaper>() {
            override fun areItemsTheSame (oldItem: Wallpaper, newItem: Wallpaper): Boolean {
                return oldItem._id == newItem._id
            }
            override fun areContentsTheSame(oldItem: Wallpaper, newItem: Wallpaper): Boolean{
                return oldItem == newItem}
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wall_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            Glide.with(context).load(currentItem.imgurl)
    //            .apply(RequestOptions().override(200, 270))
                .centerCrop()
                .into(holder.binding.wallImg)
            val pref = context.getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
            val editor = pref.edit()
            editor.putBoolean("flag", true)
            editor.apply()
            updateFlagCallback.invoke()

        }

        holder.binding.wallcardView.setOnClickListener{
            val intent = Intent(context, OpenWallActivity ::class.java)

            intent.putExtra("wall_pos", position)
             context.startActivity(intent)
        }



    }
}