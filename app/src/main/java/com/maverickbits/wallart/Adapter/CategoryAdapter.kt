package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.maverickbits.wallart.Activities.OpenOneCatWallActivity
import com.maverickbits.wallart.Activities.OpenWallActivity
import com.maverickbits.wallart.Models.CatWallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.WallLayoutBinding

class CategoryAdapter(val context: Context,private val listener: CatFavClickListener,private val updateFlagCallback: () -> Unit) :
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
            val intent = Intent(context, OpenOneCatWallActivity ::class.java)
            intent.putExtra("value",currentItem.imgurl )
            context.startActivity(intent)
        }



        //implementing fav
        holder.binding.cbHeart.setBackgroundResource(R.drawable.fav_heart)

        if (currentItem != null) {


            val sharedPref1: SharedPreferences = context.getSharedPreferences("FavPref", Context.MODE_PRIVATE)
            // Load the favorite state from SharedPreferences
            val isFavorite = sharedPref1.getBoolean(currentItem._id, false)

            // Set the heart icon based on the favorite state
            if (isFavorite) {
                holder.binding.cbHeart.setBackgroundResource(R.drawable.filled_fav_heart)
            } else {
                holder.binding.cbHeart.setBackgroundResource(R.drawable.fav_heart)
            }

            // ...

            holder.binding.cbHeart.setOnClickListener {
                val sharedPref1: SharedPreferences = context.getSharedPreferences("FavPref", Context.MODE_PRIVATE)
                val isFavorite = sharedPref1.getBoolean(currentItem._id, false)
                if (isFavorite) {
                    // Set the heart icon as gray immediately
                    holder.binding.cbHeart.setBackgroundResource(R.drawable.fav_heart)
                    // Toggle the favorite state in SharedPreferences
                    val editor = sharedPref1.edit()
                    editor.putBoolean(currentItem._id, false)
                    editor.apply()
                    val Pref: SharedPreferences = context.getSharedPreferences("deleteFav", Context.MODE_PRIVATE)
                    val editor1 = Pref.edit()
                    editor1.putBoolean("value", true)
                    editor1.apply()
//                   Toast.makeText(context, "Removed",Toast.LENGTH_SHORT).show()
                    // Perform the removal action (e.g., notify the listener)
                    listener.onItemDelete(currentItem.imgurl, currentItem._id)
                } else {
                    // Set the heart icon as red immediately
                    holder.binding.cbHeart.setBackgroundResource(R.drawable.filled_fav_heart)
//                    Toast.makeText(context, "added",Toast.LENGTH_SHORT).show()
                    // Toggle the favorite state in SharedPreferences
                    val Pref: SharedPreferences = context.getSharedPreferences("deleteFav", Context.MODE_PRIVATE)
                    val editor1 = Pref.edit()
                    editor1.putBoolean("value", true)
                    editor1.apply()
                    val editor = sharedPref1.edit()
                    editor.putBoolean(currentItem._id, true)
                    editor.apply()

                    // Perform the addition action (e.g., notify the listener)
                    listener.onItemClick(currentItem.imgurl, currentItem._id)
                }
            }

            // ...

            // ...
        }
        }
    interface CatFavClickListener{

        fun onItemClick(imgUrl: String,ImgId:String)
        fun onItemDelete(imgUrl: String,ImgId:String)

    }
    }


