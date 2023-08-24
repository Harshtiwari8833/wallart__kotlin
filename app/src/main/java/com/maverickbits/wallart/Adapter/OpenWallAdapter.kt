package com.maverickbits.wallart.Adapter

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Path.Op
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverickbits.wallart.Activities.WallpaperSetSucessActivity
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.EachWallpaperBinding

class OpenWallAdapter (val context: Context,private val activity: AppCompatActivity)
    : PagingDataAdapter<Wallpaper, OpenWallAdapter.ViewHolder>(COMPARATOR) {
    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = EachWallpaperBinding.bind(itemView)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem =  getItem(position)
        Toast.makeText(context, "hello",Toast.LENGTH_SHORT).show()
        Log.d("url123",currentItem!!.imgurl.toString())
        Glide.with(context).load(currentItem!!.imgurl).into(holder.binding.wallpaperImg)
        holder.binding.setWall.setOnClickListener {
            showSetWallpaperDialog(holder.binding.wallpaperImg,context,activity)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenWallAdapter.ViewHolder {
        return ViewHolder( LayoutInflater.from(context).inflate(R.layout.each_wallpaper,parent,false))
    }






}
private fun showSetWallpaperDialog(img: ImageView, context: Context,activity: AppCompatActivity) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Set Wallpaper")
    builder.setItems(arrayOf("Lock Screen", "Home Screen", "Both"),
        DialogInterface.OnClickListener { dialog, which ->
            val wallpaperBitmap = (img.drawable as BitmapDrawable).bitmap
            var wallpaperFlag = 0

            when (which) {
                0 -> wallpaperFlag = WallpaperManager.FLAG_LOCK
                1 -> wallpaperFlag = WallpaperManager.FLAG_SYSTEM
                2 -> wallpaperFlag = WallpaperManager.FLAG_LOCK or WallpaperManager.FLAG_SYSTEM
            }

            val wallpaperManager = WallpaperManager.getInstance(context)
            try {
                wallpaperManager.setBitmap(wallpaperBitmap, null, true, wallpaperFlag)
                val intent = Intent(context, WallpaperSetSucessActivity::class.java)
                context.startActivity(intent)
                activity.finish()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
            }
        })

    builder.show()
}