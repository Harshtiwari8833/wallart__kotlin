package com.maverickbits.wallart.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.maverickbits.wallart.databinding.ActivityOpenFavWallBinding

class OpenFavWallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenFavWallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("wall_pos1")

        Glide.with(this).load(url).into(binding.favImg)
    }
}