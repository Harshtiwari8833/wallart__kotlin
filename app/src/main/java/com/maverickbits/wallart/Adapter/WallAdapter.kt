package com.maverickbits.wallart.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions

import com.maverickbits.wallart.Activities.OpenWallActivity

import com.maverickbits.wallart.Api.Wallpaper
import com.maverickbits.wallart.R
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.databinding.WallLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WallAdapter(val context: Context, private val listener: FavClickListener, private val updateFlagCallback: () -> Unit)
    :PagingDataAdapter<Wallpaper,WallAdapter.ViewHolder>(COMPARATOR) {

    private val fullList=ArrayList<FavModel>()
    private val list = ArrayList<FavModel>()
    private lateinit var sharedPref : SharedPreferences

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
//                .apply(RequestOptions().override(1200, 200))
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


//        Implementing Fav




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
//                   Toast.makeText(context, "Removed",Toast.LENGTH_SHORT).show()
                    // Perform the removal action (e.g., notify the listener)
                    listener.onItemDelete(currentItem.imgurl, currentItem._id)
                } else {
                    // Set the heart icon as red immediately
                    holder.binding.cbHeart.setBackgroundResource(R.drawable.filled_fav_heart)
//                    Toast.makeText(context, "added",Toast.LENGTH_SHORT).show()
                    // Toggle the favorite state in SharedPreferences
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


//    fun updateList(newList:ArrayList<FavModel>){
//
//        fullList.clear()
//        fullList.addAll(newList)
//
//        list.clear()
//        list.addAll(fullList)
//        notifyDataSetChanged()
//    }

    interface FavClickListener{

        fun onItemClick(imgUrl: String,ImgId:String)
        fun onItemDelete(imgUrl: String,ImgId:String)

    }
}


