package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maverickbits.wallart.Activities.OpenCategoryActivity
import com.maverickbits.wallart.Activities.OpenWallActivity
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding

class CategoryAdapter(val context: Context,private val updateFlagCallback: () -> Unit) :
PagingDataAdapter<CatWallpaper ,CategoryAdapter.ViewHolder>(COMPARATOR){
   inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
       val binding = WallLayoutBinding.bind(itemView)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CatWallpaper>() {
            override fun areItemsTheSame(oldItem: CatWallpaper, newItem: CatWallpaper): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: CatWallpaper, newItem: CatWallpaper): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.wall_layout, parent, false))
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
            Glide.with(context).load(currentItem!!.imgurl)
                .centerCrop()
                .into(holder.binding.wallImg)
        val pref = context.getSharedPreferences("cat_animation", AppCompatActivity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("flag", true)
        editor.apply()

        updateFlagCallback.invoke()

        holder.binding.wallcardView.setOnClickListener{
            val intent = Intent(context, OpenCategoryActivity ::class.java)

            intent.putExtra("wall_pos", position)
            context.startActivity(intent)
        }
        }
    }


