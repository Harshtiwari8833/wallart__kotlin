package com.maverickbits.wallart.Activities

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.maverickbits.wallart.R
import com.maverickbits.wallart.databinding.ActivityOpenFavWallBinding
import com.maverickbits.wallart.databinding.ActivityOpenOneWallBinding

class OpenOneWallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenOneWallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var img = intent.getStringExtra("imgUrl")


        Glide.with(this).load(img).into(binding.wallpaperImg)

        binding.setWall.setOnClickListener {
            showSetWallpaperDialog(binding.wallpaperImg)
        }


    }
    private fun showSetWallpaperDialog(img: ImageView) {
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