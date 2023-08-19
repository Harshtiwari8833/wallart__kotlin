package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.maverickbits.wallart.R

class WallpaperSetSucessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper_set_sucess)
        val close = findViewById<ImageView>(R.id.close)
        close.setOnClickListener {
            onBackPressed()
        }
    }
}