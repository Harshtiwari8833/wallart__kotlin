package com.maverickbits.wallart.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.maverickbits.wallart.R

class WallpaperSetSucessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallpaper_set_sucess)
        val close = findViewById<ImageView>(R.id.close)
        close.setOnClickListener {
            onBackPressed()
        }
        val shareBtn = findViewById<Button>(R.id.button)
        shareBtn.setOnClickListener{
            Toast.makeText(this,"coming soon",Toast.LENGTH_SHORT).show()
        }
    }
}