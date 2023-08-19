package com.maverickbits.wallart.Activities

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.maverickbits.wallart.Adapter.OpenWallAdapter
import com.maverickbits.wallart.Models.WallModel
import com.maverickbits.wallart.Repositery.WallRepo
import com.maverickbits.wallart.ViewModel.OpenWallViewModel
import com.maverickbits.wallart.ViewModel.OpenWallViewModelFactory
import com.maverickbits.wallart.databinding.ActivityOpenWallBinding

class OpenWallActivity : AppCompatActivity() {
    lateinit var openWallViewModel : OpenWallViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        super.onCreate(savedInstanceState)
        val binding = ActivityOpenWallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pos = intent.extras!!.getInt("wall_pos")
        openWallViewModel = ViewModelProvider(this, OpenWallViewModelFactory(WallRepo())).get(OpenWallViewModel ::class.java)

        openWallViewModel.getwallpaper().observe(this){
            binding.viewPager.adapter = OpenWallAdapter(this, it as ArrayList<WallModel>,this)
            binding.viewPager.setCurrentItem(pos, false)
        }


    }
}