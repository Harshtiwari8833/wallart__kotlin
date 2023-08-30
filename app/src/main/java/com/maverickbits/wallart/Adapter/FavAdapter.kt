package com.maverickbits.wallart.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maverickbits.wallart.Activities.OpenFavWallActivity
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding


class FavAdapter: ListAdapter<FavModel, FavAdapter.WallpaperViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<FavModel>() {
        override fun areItemsTheSame(oldItem: FavModel, newItem: FavModel): Boolean {
            return oldItem.url == newItem.url
        }


        override fun areContentsTheSame(oldItem: FavModel, newItem: FavModel): Boolean {
            return oldItem == newItem
        }
    }

    inner class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wallpaper: FavModel) {
            // Bind data to views in the item layout
            val wallpaperImageView: ImageView = itemView.findViewById(R.id.wallImg)
//           Picasso.get().load(wallpaper.url).into(wallpaperImageView)
            Glide.with(itemView.context)
                .load(wallpaper.url)
                .apply(RequestOptions.centerCropTransform())
                .into(wallpaperImageView)

        }
        fun click(wallpaper: FavModel){
            val wallpaperImageView: ImageView = itemView.findViewById(R.id.wallImg)
            wallpaperImageView.setOnClickListener {
                val intent = Intent(itemView.context, OpenFavWallActivity::class.java)
                intent.putExtra("wall_pos1",wallpaper.url)
                intent.putExtra("wall_pos",wallpaper.id)
                itemView.context.startActivity(intent)
            }
        }

        val binding = WallLayoutBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.wall_layout, parent, false)
        return WallpaperViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val wallpaper = getItem(position)
        holder.bind(wallpaper)

        holder.click(wallpaper)
    }
}

