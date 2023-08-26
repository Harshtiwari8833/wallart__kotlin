package com.maverickbits.wallart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding

class CategoryAdapter(val context: Context) :
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

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
            Glide.with(context).load(currentItem!!.imgurl)
                .apply(RequestOptions().override(200, 270))
                .centerCrop()
                .into(holder.binding.wallImg)
        }
    }


