package com.maverickbits.wallart.Adapter

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maverickbits.wallart.Activities.WallpaperSetSucessActivity
import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.EachWallpaperBinding

class OpenCatAdapter(val context: Context, private val list: List<CatWallpaper>, private val activity: AppCompatActivity, private val updateFlagCallback: () -> Unit )
    : RecyclerView.Adapter<OpenCatAdapter.ViewHolder>() {

    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = EachWallpaperBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenCatAdapter.ViewHolder {
        return ViewHolder( LayoutInflater.from(context).inflate(R.layout.each_wallpaper,parent,false))
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: OpenCatAdapter.ViewHolder, position: Int) {
        val currentItem = list[position]
        Glide.with(context).load(currentItem.imgurl).into(holder.binding.wallpaperImg)

        val pref1 =context.getSharedPreferences("animation", AppCompatActivity.MODE_PRIVATE)
        val editor = pref1.edit()
        editor.putBoolean("falg1", true)
        editor.apply()
        updateFlagCallback.invoke()

        holder.binding.setWall.setOnClickListener {
            showSetWallpaperDialog(holder.binding.wallpaperImg,context,activity)
        }
    }
    private fun showSetWallpaperDialog(img: ImageView, context: Context, activity: AppCompatActivity) {
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
}