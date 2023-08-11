package com.maverickbits.wallart.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding

class CategoryAdapter(val context: Context, val list: ArrayList<WallModel> ):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.wall_layout, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        Glide.with(context).load(list[position].imgUrl)
            .apply(RequestOptions().override(200, 270))
            .centerCrop()
            .into(holder.binding.wallImg)

    }


    override fun getItemCount(): Int {
      return list.size
    }
    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
          val binding = WallLayoutBinding.bind(itemView)
    }
}