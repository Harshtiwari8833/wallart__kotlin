package com.maverickbits.wallart.Activities

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button

import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maverickbits.wallart.Adapter.WallAdapter
import com.maverickbits.wallart.R
import com.maverickbits.wallart.RoomDatabase.FavDatabase
import com.maverickbits.wallart.RoomDatabase.FavModel
import com.maverickbits.wallart.RoomDatabase.FavRepository
import com.maverickbits.wallart.ViewModel.FavFactoryViewModel
import com.maverickbits.wallart.ViewModel.FavViewModel

import com.maverickbits.wallart.databinding.ActivityOpenFavWallBinding

class OpenFavWallActivity: AppCompatActivity() {


    private lateinit var favViewModel : FavViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenFavWallBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val url = intent.getStringExtra("wall_pos1")
        val id = intent.getStringExtra("wall_pos")
//        val pref9 = getSharedPreferences("userEmail", AppCompatActivity.MODE_PRIVATE)
//        val email = pref9.getString("flag", "")
//        var index: Int = email!!.indexOf('@')
//        val parseEmail = email.substring(0, index)

        Glide.with(this).load(url).into(binding.favImg)
        val database = FavDatabase.getDatabase(this).getFavDao()
        val favRepository = FavRepository(database)
        favViewModel= ViewModelProvider(this,
            FavFactoryViewModel(favRepository)
        )[FavViewModel::class.java]

        //bottom sheet

        binding.favImg.setOnClickListener {

            val bottomSheetDialog =
                BottomSheetDialog(this, R.style.CustomBottomSheetDialogTheme)
            bottomSheetDialog.setContentView(R.layout.favsetwallpaperdilog)

            val remove_fav = bottomSheetDialog.findViewById<ImageView>(R.id.remove_fav)

            remove_fav!!.setOnClickListener {

                val sharedPref1: SharedPreferences = getSharedPreferences("FavPref", Context.MODE_PRIVATE)
                val Pref: SharedPreferences = getSharedPreferences("deleteFav", Context.MODE_PRIVATE)
                val editor1 = Pref.edit()
                editor1.putBoolean("value", true)
                editor1.apply()

                val favModel = FavModel(id!!,url)
                favViewModel.delete(favModel)
                val editor = sharedPref1.edit()
                editor.putBoolean(id, false)
                editor.apply()
                finish()
            }


            val set_wall = bottomSheetDialog.findViewById<Button>(R.id.set_wall)
            set_wall!!.setOnClickListener{
                showSetWallpaperDialog(binding.favImg)
                bottomSheetDialog.dismiss()

        }
            bottomSheetDialog.show()

    }

    }
    private fun showSetWallpaperDialog(img:ImageView) {
        val builder = AlertDialog.Builder(this)
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

                val wallpaperManager = WallpaperManager.getInstance(this)
                try {

                    wallpaperManager.setBitmap(wallpaperBitmap, null, true, wallpaperFlag)
                    val intent = Intent(this, WallpaperSetSucessActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show()
                }

            })

        builder.show()
    }


}

